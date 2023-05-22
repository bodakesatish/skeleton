package com.bodakesatish.skeleton.helper

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    internal const val SCHEME_DATE_FORMAT = "dd-MM-yyyy"

    fun getDateAsStringFromLong(date: Long, format: String): String {
        return android.text.format.DateFormat.format(format, getDateFromLong(date)).toString()
    }

    private fun getDateFromLong(date: Long): Date {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        calendar.set(Calendar.MILLISECOND, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.HOUR_OF_DAY, 1)
        return calendar.time
    }


    fun getCurrentDate(): String {
        val simpleDateFormat= SimpleDateFormat(SCHEME_DATE_FORMAT)
        val date = Date()
        return simpleDateFormat.format(date)
    }
}