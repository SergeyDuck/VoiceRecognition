package ru.axas.contacts.base.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar

@SuppressLint("SimpleDateFormat")
fun Long.getFormattedDate() = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss").format(this)
