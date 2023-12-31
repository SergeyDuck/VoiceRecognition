package ru.axas.contacts.common.models.local

import kotlinx.serialization.Serializable

@Serializable
data class LocalFileValue(
    val address:String? = null,
    val port:String? = null,
    val lastPost:Long? = null,
    val lastGet:Long? = null,
    val countPost:Int? = null,
    val countGet:Int? = null,
)


