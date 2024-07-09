package com.allinx.testtaskeffectivemobile.di

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.allinx.data.storage.database.dao.ImageOfferDao
import com.allinx.data.storage.database.entity.ImageOfferEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider

class RoomDbInitializer (
    private val imageOfferProvider: Provider<ImageOfferDao>
) : RoomDatabase.Callback() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            populateDatabase()
        }
    }

    private suspend fun populateDatabase() {
        populateImagesOffers()
    }

    private suspend fun populateImagesOffers() {
        PREPOPULATE_DATA.forEach { imageOffer ->
            imageOfferProvider.get().insertImageOffer(imageOffer)
        }
    }

    companion object {
        private val PREPOPULATE_DATA = listOf(
            ImageOfferEntity(1, "file:///android_asset/img_die_antwoord.jpg"),
            ImageOfferEntity(2, "file:///android_asset/img_socrat_lera.png"),
            ImageOfferEntity(3, "file:///android_asset/img_lampabikt.png"),)
    }
}