package com.allinx.data.repository

import com.allinx.data.storage.database.dao.ImageOfferDao
import com.allinx.data.storage.network.retrofit.TicketsApiService
import com.allinx.data.storage.preferences.ISearchPreferences
import com.allinx.domain.models.offer.ImageOffer
import com.allinx.domain.models.offer.OfferWithImage
import com.allinx.domain.models.offer.Offers
import com.allinx.domain.models.offerticket.OffersTickets
import com.allinx.domain.models.ticket.Tickets
import com.allinx.domain.repository.ITicketsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TicketRepository(
    private val ticketsApiService: TicketsApiService,
    private val searchPreferences: ISearchPreferences,
    private val imageOfferDao: ImageOfferDao
    ) : ITicketsRepository {

    override suspend fun getOffers(): Offers {
        return withContext(Dispatchers.IO){
            val dataOffers = ticketsApiService.getOffers()
            val offers = dataOffers.toDomain()
            offers
        }
    }

    override suspend fun getOffersTickets(): OffersTickets {
        return withContext(Dispatchers.IO){
            val dataOffersTickets = ticketsApiService.getOffersTickets()
            val offersTickets = dataOffersTickets.toDomain()
            offersTickets
        }
    }

    override suspend fun getTickets(): Tickets {
        return withContext(Dispatchers.IO){
            val dataTickets = ticketsApiService.getTickets()
            val tickets = dataTickets.toDomain()
            tickets
        }
    }

    override fun getSavedCityFrom(): String? {
        return searchPreferences.getCityFrom()
    }

    override fun saveCityFrom(city: String) {
        searchPreferences.saveCityFrom(city)
    }

    override suspend fun getImageOfferById(id: Int): ImageOffer {
        return imageOfferDao.getImageOfferById(id).toDomain()
    }

    override suspend fun getImagesOffers(): List<ImageOffer> {
        return imageOfferDao.getImagesOffers().map {
            it.toDomain()
        }
    }

    override suspend fun getOffersWithImages(): List<OfferWithImage> {
        return withContext(Dispatchers.IO){
            val offers = getOffers()
            val listOffersWithImages = mutableListOf<OfferWithImage>()
            for (offer in offers.offers){
                val image = getImageOfferById(id = offer.id)
                val offerWithImage = OfferWithImage(
                    id = offer.id,
                    title = offer.title,
                    town = offer.town,
                    price = offer.price,
                    image = image.image
                )
                listOffersWithImages.add(offerWithImage)
            }
            listOffersWithImages
        }
    }
}