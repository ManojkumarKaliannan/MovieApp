package com.backbase.assignment.ui.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun View.ShowSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}
fun convertDateFormat(date: String?): String {
    date?.let {
        val parser =  SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())
        val formatter = SimpleDateFormat("MMMM dd,yyyy",Locale.getDefault())
        val formattedDate = parser.parse(date)?.let { formatter.format(it) }
        return formattedDate.toString()
    }
    return ""
}
fun convertHoursFormat(minutes: String?):String{
    minutes?.let {
        var sdf = SimpleDateFormat("mm",Locale.getDefault())
        val dt = sdf.parse(minutes)
        sdf = SimpleDateFormat("h:mm",Locale.getDefault())
        return customTimeFormat(dt?.let { sdf.format(it) }.toString())
    }
    return ""
}

fun customTimeFormat(time: String):String{
    val stringArray = time.split(":")
    return stringArray[0] +"h"+stringArray[1]
}
fun showAlert(context: Activity){
    AlertDialog.Builder(context)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setTitle("App Exit")
        .setMessage("Are you sure you want to Exit?")
        .setPositiveButton("Yes") { _, _ -> ActivityCompat.finishAffinity(context) }
        .setNegativeButton("No", null)
        .show()
}

