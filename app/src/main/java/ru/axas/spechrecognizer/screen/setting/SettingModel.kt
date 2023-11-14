package ru.axas.contacts.screen.setting

import cafe.adriel.voyager.core.model.coroutineScope
import ru.axas.contacts.base.BaseModel
import ru.axas.contacts.store.FileStoreApp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingModel (
    private val dataStore: FileStoreApp,
) : BaseModel() {



}