package com.allinx.testtaskeffectivemobile.presentation.tickets.offerstickets.mapper

import com.allinx.domain.models.offerticket.OfferTicketItem
import com.allinx.testtaskeffectivemobile.Constants.TitlesForColors.TITLE_FIRST
import com.allinx.testtaskeffectivemobile.Constants.TitlesForColors.TITLE_SECOND
import com.allinx.testtaskeffectivemobile.Constants.TitlesForColors.TITLE_THIRD
import com.allinx.testtaskeffectivemobile.R
import com.allinx.testtaskeffectivemobile.presentation.tickets.offerstickets.model.OfferTicketWithColor

object OfferTicketMapper {
    private val listColors = listOf(R.color.bittersweet, R.color.cerulean_blue, R.color.white)

    fun toOfferTicketWithColor(offerTicketItem: OfferTicketItem): OfferTicketWithColor {
        return OfferTicketWithColor(
            id = offerTicketItem.id,
            title = offerTicketItem.title,
            price = offerTicketItem.price,
            timeRange = toSingleLine(offerTicketItem.timeRange),
            color = getColor(offerTicketItem.title)
        )
    }

   private fun toSingleLine(array: List<String>): String {
        var line = ""
        for(item in array){
            line += "$item "
        }
        line = line.substring(0,line.length - 1)
        return line
    }

    private fun getColor(title: String): Int {
        return when(title){
            TITLE_FIRST -> listColors[0]
            TITLE_SECOND -> listColors[1]
            TITLE_THIRD -> listColors[2]
            else -> listColors[0]
        }
    }
}