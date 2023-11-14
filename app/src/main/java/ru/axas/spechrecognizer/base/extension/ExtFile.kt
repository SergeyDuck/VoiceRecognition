package ru.axas.contacts.base.extension

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.webkit.MimeTypeMap
import androidx.core.content.FileProvider
import ru.axas.contacts.common.models.res.TextApp
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val DEFAULT_CHANNEL_ID_FCM_DOWNLOAD = "fcm_default_channel_download"
fun patchFromPictures(fileName: String): File {
    val directory = File(
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
        TextApp.FOLDER_NAME
    )
    directory.mkdirs()
    return File(directory, fileName)
}

fun getFileMimeType(fileName: String): String? {
    val fileExtension = MimeTypeMap.getFileExtensionFromUrl(fileName)
    return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension)
}

@Throws(IOException::class)
fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", LocaleRu).format(Date())
    val storageDir = cacheDir
    return File.createTempFile(
        "JPEG_${timeStamp}_", /* prefix */
        ".jpg", /* suffix */
        storageDir /* directory */
    ).apply {
        deleteOnExit()
    }
}

private val LocaleRu = Locale("ru", "RU")

fun Context.makeUri() = this.createImageFile().getUriForFile(this)

fun File.getUriForFile(context: Context): Uri {
    return FileProvider.getUriForFile(
        context,
        "${context.applicationInfo.packageName}.fileprovider",
        this
    )
}


private fun convertToDegree(coordinate: String): Float {
    val parts = coordinate.split(",").map { it.trim() }
    val degrees = parts[0].toDouble()
    val minutes = parts[1].toDouble()
    val seconds = parts[2].toDouble()

    return (degrees + minutes / 60 + seconds / 3600).toFloat()
}
