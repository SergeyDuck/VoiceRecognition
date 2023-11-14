package ru.axas.contacts.di.module

import ru.axas.contacts.network.Client
import ru.axas.contacts.network.api.ApiAlbums
import ru.axas.contacts.screen.home.HomeMainModel
import ru.axas.contacts.screen.setting.SettingModel
import ru.axas.contacts.screen.splash.SplashScreenModel
import ru.axas.contacts.usecase.VoiceUseCase
import org.koin.dsl.module


val setModels = module {
    single { Client() }
    single { SplashScreenModel(get()) }

    factory { HomeMainModel(get(), get(), get()) }
    factory { SettingModel(get()) }
    factory { VoiceUseCase(get()) }
    factory { ApiAlbums(get(), get()) }
}