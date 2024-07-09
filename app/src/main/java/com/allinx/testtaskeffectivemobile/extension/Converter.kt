package com.allinx.testtaskeffectivemobile.extension

object Converter {
    fun getStringPrice(price: Int): String{
        val rmd =  price - (price/1000)*1000
        return if (rmd < 10) {
            "00$rmd"
        } else if (rmd < 100) {
            "0$rmd"
        } else
            rmd.toString()
    }
}