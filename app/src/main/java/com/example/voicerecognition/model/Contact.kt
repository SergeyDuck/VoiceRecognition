package com.example.voicerecognition.model

import com.google.gson.annotations.SerializedName

data class Contact(
    val phone: String,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    val company: String
)
