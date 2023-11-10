package ru.axas.spechrecognizer.screen.home

import android.content.Context
import cafe.adriel.voyager.core.model.coroutineScope
import ru.axas.spechrecognizer.base.BaseModel
import ru.axas.spechrecognizer.common.memory.gDLoaderStart
import ru.axas.spechrecognizer.common.memory.gDLoaderStop
import ru.axas.spechrecognizer.common.models.logger.LogCustom
import ru.axas.spechrecognizer.screen.setting.SettingScreen
import ru.axas.spechrecognizer.screen.splash.SplashScreenModel
import ru.axas.spechrecognizer.usecase.VoiceUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeMainModel(
    private val context: Context,
    private val useCase: VoiceUseCase
) : BaseModel() {

    private val _responseVoice = MutableStateFlow("")
    val responseVoice = _responseVoice.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun postText(text: String) = coroutineScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        useCase.postText(
            text = text,
            flowStart = { gDLoaderStart() },
            flowSuccess = { _responseVoice.value = it ?: "" },
            flowStop = { gDLoaderStop() },
        )

    }
     fun goToSetting()  {
        navigator.push(SettingScreen())
    }
}