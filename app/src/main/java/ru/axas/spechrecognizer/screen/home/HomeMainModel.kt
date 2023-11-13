package ru.axas.spechrecognizer.screen.home

import android.content.Context
import cafe.adriel.voyager.core.model.coroutineScope
import ru.axas.spechrecognizer.common.models.util.addContact
import ru.axas.spechrecognizer.common.models.util.deleteAllContacts
import ru.axas.spechrecognizer.common.models.util.getContacts
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.axas.spechrecognizer.base.BaseModel
import ru.axas.spechrecognizer.common.memory.gDLoaderStart
import ru.axas.spechrecognizer.common.memory.gDLoaderStop
import ru.axas.spechrecognizer.common.memory.gDMessage
import ru.axas.spechrecognizer.common.models.local.LocalFileValue
import ru.axas.spechrecognizer.common.models.logger.LogCustom
import ru.axas.spechrecognizer.common.models.util.isNetworkAvailable
import ru.axas.spechrecognizer.network.model.Contact
import ru.axas.spechrecognizer.screen.setting.SettingScreen
import ru.axas.spechrecognizer.store.FileStoreApp
import ru.axas.spechrecognizer.usecase.VoiceUseCase

class HomeMainModel(
    private val context: Context,
    private val useCase: VoiceUseCase,
    private val dataStore: FileStoreApp,
) : BaseModel() {

    private val _responseContacts = MutableStateFlow<List<Contact>>(listOf())
    val responseContacts = _responseContacts.asStateFlow()

    private val _timeSend = MutableStateFlow<Long?>(null)
    val timeSend = _timeSend.asStateFlow()

    private val _timeGet = MutableStateFlow<Long?>(null)
    val timeGet = _timeGet.asStateFlow()

    private val _countSend = MutableStateFlow<Int?>(null)
    val countSend = _countSend.asStateFlow()

    private val _countGet = MutableStateFlow<Int?>(null)
    val countGet = _countGet.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }


    private fun getLastPost() = coroutineScope.launch(Dispatchers.IO) {
        _timeSend.value = dataStore.getLocalData().lastPost
        _countSend.value = dataStore.getLocalData().countPost
    }

    private fun getLastGet() = coroutineScope.launch(Dispatchers.IO) {
        _timeGet.value = dataStore.getLocalData().lastGet
        _countGet.value = dataStore.getLocalData().countGet
    }

    fun getLast() {
        getLastPost()
        getLastGet()
    }

    fun postData() =
        coroutineScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            if (!isNetworkAvailable(context = context)) {
                gDMessage("Нет подключения к интернету!")
                return@launch
            }
            val contacts = getContacts(context = context)
            LogCustom.logD("s: $contacts")
            if (contacts.isEmpty())
                gDMessage("Контакты не найдены!")
            else {
                useCase.postData(
                    listContact = contacts,
                    flowStart = { gDLoaderStart() },
                    flowSuccess = {
                        gDMessage("Контакты отправлены успешно")
                        val time = System.currentTimeMillis()
                        _timeSend.value = time
                        dataStore.updateLocalData { LocalFileValue().copy(lastPost = time) }
                        val count = contacts.size
                        _countSend.value = count
                        dataStore.updateLocalData { LocalFileValue().copy(countPost = count) }
                    },
                    flowStop = { gDLoaderStop() },
                    flowError = { LogCustom.logE("Error SEND DATA", contacts) },
                )
            }

        }

    fun getData() =
        coroutineScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            if (!isNetworkAvailable(context = context)) {
                gDMessage("Нет подключения к интернету!")
                return@launch
            }
            deleteAllContacts(context = context)
            useCase.getData(
                flowStart = { gDLoaderStart() },
                flowSuccess = {
                    _responseContacts.value = it
                    it.forEach { contact ->
                        addContact(
                            context = context, list = listOf(
                                Contact(
                                    phone = contact.phone,
                                    firstName = contact.firstName,
                                    lastName = contact.lastName,
                                    company = contact.company
                                )
                            )
                        )
                    }
                    gDMessage("Контакты сохранены успешно!")
                    val time = System.currentTimeMillis()
                    _timeGet.value = time
                    dataStore.updateLocalData { LocalFileValue().copy(lastGet = time) }
                    val count = it.size
                    _countGet.value = count
                    dataStore.updateLocalData { LocalFileValue().copy(countGet = count) }

                },
                flowStop = { gDLoaderStop() })
        }

    fun goToSetting() {
        navigator.push(SettingScreen())
    }
}