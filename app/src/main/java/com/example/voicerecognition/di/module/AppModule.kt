package com.example.voicerecognition.di.module

import com.example.voicerecognition.screen.home.HomeMainModel
import com.example.voicerecognition.screen.splash.SplashScreenModel
import org.koin.dsl.module


val setModels = module {
    single { SplashScreenModel(get()) }

    factory { HomeMainModel(get()) }
}