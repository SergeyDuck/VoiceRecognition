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

    const val baseTextNameApp: String = "Spech Recognizer"
    const val FOLDER_NAME: String = "FAMILY_VIBE"


}