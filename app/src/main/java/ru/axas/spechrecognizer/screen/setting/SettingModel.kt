package ru.axas.spechrecognizer.screen.setting

import cafe.adriel.voyager.core.model.coroutineScope
import ru.axas.spechrecognizer.base.BaseModel
import ru.axas.spechrecognizer.store.FileStoreApp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingModel (
    private val dataStore: FileStoreApp,
) : BaseModel() {

    private val _domain = MutableStateFlow(dataStore.getLocalData().address?:"")
    val domain = _domain.asStateFlow()

    private val _url = MutableStateFlow(dataStore.getLocalData().port?:"")
    val url = _url.asStateFlow()


    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun changeAddress(text: String) = coroutineScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        dataStore.updateLocalData { it.copy(address = text) }
    }

    fun changePort(text: String) = coroutineScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        dataStore.updateLocalData { it.copy(port = text) }
    }

}