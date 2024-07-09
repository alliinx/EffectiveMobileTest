package com.allinx.data.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.allinx.data.storage.database.entity.ImageOfferEntity

@Dao
interface ImageOfferDao {
    @Query("SELECT * FROM images_offers")
    suspend fun getImagesOffers () : List<ImageOfferEntity>

    @Query("SELECT * FROM images_offers WHERE id = :id")
    suspend fun getImageOfferById (id: Int) : ImageOfferEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImageOffer(imageOfferEntity: ImageOfferEntity)
}