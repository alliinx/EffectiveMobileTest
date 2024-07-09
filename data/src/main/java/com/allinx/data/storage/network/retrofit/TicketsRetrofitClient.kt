package com.allinx.data.storage.network.retrofit

import android.app.Application
import com.allinx.data.storage.network.Constants.BASE_URL
import com.allinx.data.storage.network.okhttp.TicketsOkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TicketsRetrofitClient {
    lateinit var client: Retrofit
        private set

    fun initialize(application: Application) {
        val cocktailsOkHttpClient = TicketsOkHttpClient(application)
        client = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(cocktailsOkHttpClient.client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}