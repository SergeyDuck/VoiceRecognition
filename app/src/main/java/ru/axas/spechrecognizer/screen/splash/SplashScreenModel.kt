package ru.axas.spechrecognizer.screen.splash

import android.content.Context
import cafe.adriel.voyager.core.model.coroutineScope
import ru.axas.spechrecognizer.base.BaseModel
import ru.axas.spechrecognizer.common.memory.gDLoaderStart
import ru.axas.spechrecognizer.common.memory.gDLoaderStop
import ru.axas.spechrecognizer.common.models.logger.LogCustom.logE
import ru.axas.spechrecognizer.screen.home.HomeMainScreen
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


