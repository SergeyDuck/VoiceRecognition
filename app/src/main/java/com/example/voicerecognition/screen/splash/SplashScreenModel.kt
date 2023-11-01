package com.example.voicerecognition.screen.splash

import android.content.Context
import cafe.adriel.voyager.core.model.coroutineScope
import com.example.voicerecognition.base.BaseModel
import com.example.voicerecognition.common.memory.gDLoaderStart
import com.example.voicerecognition.common.memory.gDLoaderStop
import com.example.voicerecognition.common.models.logger.LogCustom.logE
import com.example.voicerecognition.screen.home.HomeMainScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashScreenModel(

    private val context: Context
) : BaseModel() {

    companion object {
        private const val delayMillis = 1200L
    }

    fun startApp() = coroutineScope.launch {
        delay(delayMillis)
        goToMain()
    }


    private fun goToMain() = coroutineScope.launch {
        delay(delayMillis)
        logE("goToMain")
        navigator.push(HomeMainScreen())
    }
}


