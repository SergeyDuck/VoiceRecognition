package ru.axas.contacts.store.di

import android.content.Context
import java.io.File

class ProvideFileImpl(private val context: Context): ProvideFile {

    override fun getFileMain(): File {
        return File(context.filesDir, "user_settings${context.packageName}.json")
    }

    override  fun getFileCrypto(): File {
        return File(context.filesDir, "crypto_row_${context.packageName}.json")
    }
}