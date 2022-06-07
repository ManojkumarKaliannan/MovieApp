package com.backbase.assignment.ui.utils

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


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


