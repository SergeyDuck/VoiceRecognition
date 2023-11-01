package com.example.voicerecognition.network.model

class SendingData(val message: String?) {
    companion object {
        fun mapFrom(text: String) = SendingData(text)
    }
}