package com.devstart.protoenergy.util


import java.text.SimpleDateFormat
import java.util.*

object DateConverter {
    fun convertData(date: String): String {
        val convLong: Long

        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        convLong = dateFormat.parse(date).time / 1000
        val simpleDateFormat = SimpleDateFormat("dd MMM, yyyy 'at' HH:mm aa", Locale.getDefault())
        val date = Date(convLong * 1000)
        return simpleDateFormat.format(date)
    }
}