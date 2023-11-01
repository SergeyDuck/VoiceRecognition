package com.example.voicerecognition.store.di


import com.example.voicerecognition.store.FileStoreApp
import org.koin.dsl.module


fun providersModuleDataStore(fileProvide: ProvideFile)= module {
    single { FileStoreApp(fileProvide) }
}

