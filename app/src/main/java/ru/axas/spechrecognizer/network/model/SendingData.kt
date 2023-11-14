package ru.axas.contacts.network.model

class SendingData(val message: String?) {
    companion object {
        fun mapFrom(text: String) = SendingData(text)
    }
}