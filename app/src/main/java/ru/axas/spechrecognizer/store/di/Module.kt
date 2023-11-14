package ru.axas.contacts.store.di


import ru.axas.contacts.store.FileStoreApp
import org.koin.dsl.module


fun providersModuleDataStore(fileProvide: ProvideFile)= module {
    single { FileStoreApp(fileProvide) }
}

