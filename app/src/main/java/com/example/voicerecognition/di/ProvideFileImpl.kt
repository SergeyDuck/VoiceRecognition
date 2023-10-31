package com.example.voicerecognition.di

import android.content.Context
import java.io.File

class ProvideFileImpl(private val context: Context) {

    fun getFileMain(): File {
        return File(context.filesDir, "user_settings${context.packageName}.json")
    }

    fun getFileCrypto(): File {
        return File(context.filesDir, "crypto_row_${context.packageName}.json")
    }
}