package com.example.voicerecognition.di.module

import com.example.voicerecognition.network.Client
import com.example.voicerecognition.network.api.ApiAlbums
import com.example.voicerecognition.screen.home.HomeMainModel
import com.example.voicerecognition.screen.splash.SplashScreenModel
import com.example.voicerecognition.usecase.VoiceUseCase
import org.koin.dsl.module


val setModels = module {
    single { Client() }
    single { SplashScreenModel(get()) }

    factory { HomeMainModel(get(), get()) }
    factory { VoiceUseCase(get()) }
    factory { ApiAlbums(get()) }
}