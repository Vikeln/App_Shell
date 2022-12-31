package tech.nouveta.demo.utils

import java.text.SimpleDateFormat
import java.util.*

class DateFormatter {


    fun currentTimeToLong(): Long {
        return System.currentTimeMillis()
    }

    fun convertDateToLong(date: String): Long {
        val df = SimpleDateFormat("yyyy.MM.dd HH:mm")
        return df.parse(date).time
    }
}