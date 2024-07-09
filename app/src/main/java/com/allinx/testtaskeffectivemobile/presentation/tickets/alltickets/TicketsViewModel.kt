package com.allinx.testtaskeffectivemobile.presentation.tickets.alltickets

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinx.domain.models.offer.OfferWithImage
import com.allinx.domain.models.ticket.Tickets
import com.allinx.domain.usecases.GetTicketsUseCase
import com.allinx.testtaskeffectivemobile.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketsViewModel @Inject constructor(
    private val getTicketsUseCase: GetTicketsUseCase
) : ViewModel() {

    private val ticketsMutable = MutableLiveData<Tickets>()
    val resultTickets: LiveData<Tickets> = ticketsMutable

    private val _uiMessageChannel: MutableSharedFlow<Int> = MutableSharedFlow()
    val uiMessageChannel = _uiMessageChannel.asSharedFlow()

    private val loadingStateMutable = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = loadingStateMutable

    fun getTickets() {
        loadingStateMutable.value = true
        viewModelScope.launch {
            try {
                val tickets = getTicketsUseCase.execute()
                ticketsMutable.value = tickets
            } catch (e: Exception) {
                _uiMessageChannel.emit(R.string.error)
            } finally {
                loadingStateMutable.value = false
            }
        }
    }
}