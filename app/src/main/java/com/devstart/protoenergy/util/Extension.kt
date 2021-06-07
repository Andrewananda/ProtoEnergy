package com.devstart.protoenergy.util

import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat
import java.text.NumberFormat

fun View.snack(message: String, duration: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, message, duration).show()
}

fun View.hide() {
    visibility = View.GONE
}

fun View.show(){
    visibility = View.VISIBLE
}

fun numberFormat(number: Double): String {
    val formatter: NumberFormat = DecimalFormat("#,###")
    val myNumber = number
    return formatter.format(myNumber)
}