package com.devstart.protoenergy.util



import java.text.SimpleDateFormat
import java.util.*

object DateConverter {
    fun convertData(date: String): Date {
        val dateValString = SimpleDateFormat("yyyy-MM-d'T'HH:mm").parse(date)

        return dateValString
    }
}