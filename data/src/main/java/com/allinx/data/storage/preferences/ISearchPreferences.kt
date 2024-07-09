package com.allinx.data.storage.preferences

interface ISearchPreferences {
    fun saveCityFrom(city: String)
    fun getCityFrom(): String?
}