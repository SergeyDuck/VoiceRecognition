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

    private val _domain = MutableStateFlow(dataStore.getLocalData().domen?:"")
    val domain = _domain.asStateFlow()

    private val _url = MutableStateFlow(dataStore.getLocalData().url?:"")
    val url = _url.asStateFlow()


    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun changeDomain(text: String) = coroutineScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        dataStore.updateLocalData { it.copy(domen = text) }
    }

    fun changeUrl(text: String) = coroutineScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        dataStore.updateLocalData { it.copy(url = text) }
    }

}