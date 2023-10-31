package com.example.voicerecognition.common.base.util

import android.content.Context
import androidx.core.net.toFile
import androidx.core.net.toUri
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.default
import java.io.File

suspend fun compressFile(patchFile: String, context: Context? = null): File {
    return context?.let {
        Compressor.compress(context, patchFile.toUri().toFile()) {
            default(width = 1024)
        }
    } ?: patchFile.toUri().toFile()
}

