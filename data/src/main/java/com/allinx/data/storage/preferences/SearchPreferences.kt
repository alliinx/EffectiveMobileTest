package com.allinx.data.storage.preferences

import android.content.Context
import android.content.SharedPreferences
import com.allinx.data.storage.preferences.Constants.SAVED_CITY_FROM
import com.allinx.data.storage.preferences.Constants.SEARCH_PREFS

class SearchPreferences(context: Context) : ISearchPreferences {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SEARCH_PREFS, Context.MODE_PRIVATE)

    override fun saveCityFrom(city: String) {
        sharedPreferences.edit().putString(SAVED_CITY_FROM, city).apply()
    }

    override fun getCityFrom(): String? {
        return sharedPreferences.getString(SAVED_CITY_FROM, "")
    }
}