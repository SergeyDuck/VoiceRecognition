package ru.axas.spechrecognizer.di.module

import ru.axas.spechrecognizer.network.Client
import ru.axas.spechrecognizer.network.api.ApiAlbums
import ru.axas.spechrecognizer.screen.home.HomeMainModel
import ru.axas.spechrecognizer.screen.setting.SettingModel
import ru.axas.spechrecognizer.screen.splash.SplashScreenModel
import ru.axas.spechrecognizer.usecase.VoiceUseCase
import org.koin.dsl.module


val setModels = module {
    single { Client() }
    single { SplashScreenModel(get()) }

    factory { HomeMainModel(get(), get(), get()) }
    factory { SettingModel(get()) }
    factory { VoiceUseCase(get()) }
    factory { ApiAlbums(get(), get()) }
}