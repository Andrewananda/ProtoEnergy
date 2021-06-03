package com.devstart.protoenergy.util


import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

object DateConverter {
    @RequiresApi(Build.VERSION_CODES.N)
    fun convertData(date: String): Date {
        val dateValString = SimpleDateFormat("yyyy-MM-d'T'HH:mm").parse(date)

        return dateValString
    }
}