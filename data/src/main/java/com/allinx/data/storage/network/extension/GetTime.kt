package com.allinx.data.storage.network.extension

import com.allinx.data.storage.network.Constants.DATE_FORMAT_PATTERN
import java.text.SimpleDateFormat
import java.util.Locale


object GetTime {
    fun getTime(date: String): String {
        return date.substring(11,16)
    }

    fun getTripTime(departmentDate: String, arrivalDate: String): String {
        val dateFormat = SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.getDefault())
        val dateStart = dateFormat.parse(departmentDate)
        val dateEnd = dateFormat.parse(arrivalDate)

        val diff: Long = (dateEnd?.time ?: 0) - (dateStart?.time ?: 0)
        val hours = (diff / (1000 * 60 * 60)).toInt()
        val minutes = (diff / (1000 * 60)).toInt()
        val hoursDouble = minutes.toDouble()/60
        return if (hoursDouble - hours in 0.0..0.25){
            hours.toString()
        } else if (hoursDouble - hours in 0.25..0.75){
            (hours+0.5).toString()
        } else {
            (hours+1).toString()
        }
    }
}