package ru.axas.contacts.common.models.util

import kotlinx.datetime.Clock
import java.text.SimpleDateFormat
import java.util.Locale


fun String.onlyDigit() = Regex("[^0-9]").replace(this, "")

private val LocaleRuTime = Locale("ru", "RU")

fun Long.millDateDDMMYYYY(): String =
    SimpleDateFormat("dd.MM.yyyy", LocaleRuTime).format(this)

fun Long.millDateDDMMYYYYHHMM(): String =
    SimpleDateFormat("dd.MM.yyyy  HH:mm", LocaleRuTime).format(this)

fun Long.millDateDDMMYYYYg(): String =
    SimpleDateFormat("dd MMMM yyyy г.", LocaleRuTime).format(this)

fun Long.millDataHHMM(): String =
    SimpleDateFormat("HH:mm", LocaleRuTime).format(this)

fun nowTime(): Long = Clock.System.now().toEpochMilliseconds()

fun Long.millToDay(): Int = SimpleDateFormat("dd", LocaleRuTime).format(this).toIntOrNull() ?: 0
fun Long.millToYer(): Int = SimpleDateFormat("yyyy", LocaleRuTime).format(this).toIntOrNull() ?: 0
fun Long.millToMonth(): Int = SimpleDateFormat("MM", LocaleRuTime).format(this).toIntOrNull() ?: 0


fun Long.formatTimeElapsed(): String {
    val currentTime = nowTime()
    val elapsedTime = currentTime - this
    return when {
        elapsedTime < 60_000     -> "только что"
        elapsedTime < 3600_000   -> "${elapsedTime / 60000} минут назад"
        elapsedTime < 86400_000  -> "сегодня в ${this.millDataHHMM()}"
        elapsedTime < 172800_000 -> "вчера в ${this.millDataHHMM()}"
        else                     -> this.millDateDDMMYYYYHHMM()
    }
}

