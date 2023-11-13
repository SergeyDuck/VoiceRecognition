package ru.axas.spechrecognizer.common.models.res

object TextApp {

    fun initDebugAndVersion(isDebugInit: Boolean, versionNameInit: String) {
        isDebug = isDebugInit
        versionName = versionNameInit
    }

    @Volatile
    var isDebug = false

    @Volatile
    var versionName: String = ""

    const val baseTextNameApp: String = "Contacts"
    const val FOLDER_NAME: String = "CONTACTS"


}