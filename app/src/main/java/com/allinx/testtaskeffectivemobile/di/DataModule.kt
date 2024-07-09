package com.allinx.testtaskeffectivemobile.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.allinx.data.storage.network.okhttp.TicketsOkHttpClient
import com.allinx.data.storage.network.retrofit.TicketsApiService
import com.allinx.data.storage.network.retrofit.TicketsRetrofitClient
import com.allinx.data.repository.TicketRepository
import com.allinx.data.storage.database.ImageOfferDatabase
import com.allinx.data.storage.database.dao.ImageOfferDao
import com.allinx.data.storage.preferences.ISearchPreferences
import com.allinx.data.storage.preferences.SearchPreferences
import com.allinx.domain.repository.ITicketsRepository
import com.allinx.testtaskeffectivemobile.Constants.Data.APP_PREFS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideRetrofit(application: Application): Retrofit {
        TicketsRetrofitClient.initialize(application)
        return TicketsRetrofitClient.client
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(application: Application): OkHttpClient {
        return TicketsOkHttpClient(application).client
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSearchPreferences(@ApplicationContext context: Context): ISearchPreferences {
        return SearchPreferences(context)
    }

    @Provides
    @Singleton
    fun provideTicketApiService(retrofit: Retrofit): TicketsApiService {
        return retrofit.create(TicketsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context, imageOfferProvider: Provider<ImageOfferDao>): ImageOfferDatabase {
        return Room.databaseBuilder(
            context,
            ImageOfferDatabase::class.java,
            "image_offer_database"
        ).addCallback(
            RoomDbInitializer(imageOfferProvider)
        ).build()
    }

    @Provides
    @Singleton
    fun provideImageOfferDao(database: ImageOfferDatabase): ImageOfferDao {
        return database.imageOfferDao()
    }

    @Provides
    @Singleton
    fun provideTicketRepository(
        apiService: TicketsApiService,
        searchPrefs: ISearchPreferences,
        imageOfferDao: ImageOfferDao
    ): ITicketsRepository {
        return TicketRepository (apiService, searchPrefs, imageOfferDao)
    }

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }
}