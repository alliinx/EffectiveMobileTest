package com.allinx.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.allinx.data.storage.database.dao.ImageOfferDao
import com.allinx.data.storage.database.entity.ImageOfferEntity
import com.allinx.data.storage.network.Constants.DATABASE_VERSION_CODE

@Database(entities = [ImageOfferEntity::class], version = DATABASE_VERSION_CODE)
abstract class ImageOfferDatabase : RoomDatabase() {
    abstract fun imageOfferDao(): ImageOfferDao
}