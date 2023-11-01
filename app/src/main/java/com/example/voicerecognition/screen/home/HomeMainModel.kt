package com.example.voicerecognition.screen.home

import android.content.Context
import cafe.adriel.voyager.core.model.coroutineScope
import com.example.voicerecognition.base.BaseModel
import com.example.voicerecognition.common.memory.gDLoaderStart
import com.example.voicerecognition.common.memory.gDLoaderStop
import com.example.voicerecognition.usecase.VoiceUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeMainModel(
    private val context: Context,
    private val useCase: VoiceUseCase
) : BaseModel() {

    private val _responseVoice = MutableStateFlow("")
    val responseVoice = _responseVoice.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun postText(text: String) = coroutineScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
        useCase.postText(
            text = text,
            flowStart = { gDLoaderStart() },
            flowSuccess = { _responseVoice.value = it ?: "" },
            flowStop = { gDLoaderStop() },
        )

    }
}