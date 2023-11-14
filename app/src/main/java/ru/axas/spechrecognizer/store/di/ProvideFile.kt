package ru.axas.contacts.store.di

import java.io.File

interface ProvideFile {
    fun getFileMain(): File
    fun getFileCrypto(): File
}
