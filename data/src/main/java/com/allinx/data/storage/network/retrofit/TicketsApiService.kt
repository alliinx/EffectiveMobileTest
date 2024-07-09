package com.allinx.data.storage.network.retrofit

import com.allinx.data.storage.network.Constants.GET_OFFERS
import com.allinx.data.storage.network.Constants.GET_OFFERS_TICKETS
import com.allinx.data.storage.network.Constants.GET_TICKETS
import com.allinx.data.storage.network.dto.offer.OffersDto
import com.allinx.data.storage.network.dto.offertickets.OffersTicketsDto
import com.allinx.data.storage.network.dto.tickets.TicketsDto
import retrofit2.http.GET

interface TicketsApiService {
    @GET(GET_OFFERS)
    suspend fun getOffers(): OffersDto

    @GET(GET_OFFERS_TICKETS)
    suspend fun getOffersTickets(): OffersTicketsDto

    @GET(GET_TICKETS)
    suspend fun getTickets(): TicketsDto
}