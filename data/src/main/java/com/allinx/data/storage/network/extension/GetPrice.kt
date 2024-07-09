package com.allinx.data.storage.network.extension

object GetPrice {
    fun getRmdPrice(price: Int): String{
        val rmd =  price - (price/1000)*1000
        return if (rmd < 10) {
            "00$rmd"
        } else if (rmd < 100) {
            "0$rmd"
        } else
            rmd.toString()
    }
    fun getThousandsPrice(price: Int) = price/1000
}