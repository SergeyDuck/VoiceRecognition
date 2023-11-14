package ru.axas.contacts.screen.splash

import android.content.Context
import cafe.adriel.voyager.core.model.coroutineScope
import ru.axas.contacts.base.BaseModel
import ru.axas.contacts.common.memory.gDLoaderStart
import ru.axas.contacts.common.memory.gDLoaderStop
import ru.axas.contacts.common.models.logger.LogCustom.logE
import ru.axas.contacts.screen.home.HomeMainScreen
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


