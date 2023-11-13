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



}