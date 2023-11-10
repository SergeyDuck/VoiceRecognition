package ru.axas.spechrecognizer.store.di


import ru.axas.spechrecognizer.store.FileStoreApp
import org.koin.dsl.module


fun providersModuleDataStore(fileProvide: ProvideFile)= module {
    single { FileStoreApp(fileProvide) }
}

