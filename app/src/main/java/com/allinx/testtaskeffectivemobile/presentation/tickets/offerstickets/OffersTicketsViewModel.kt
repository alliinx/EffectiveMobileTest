package com.allinx.testtaskeffectivemobile.presentation.tickets.offerstickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinx.domain.models.offerticket.OffersTickets
import com.allinx.domain.usecases.GetOffersTicketsUseCase
import com.allinx.testtaskeffectivemobile.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OffersTicketsViewModel @Inject constructor(
    private val getOffersTicketsUseCase: GetOffersTicketsUseCase
): ViewModel() {

    private val offersTicketsMutable = MutableLiveData<OffersTickets>()
    val resultOffersTickets: LiveData<OffersTickets> = offersTicketsMutable

    private val _uiMessageChannel: MutableSharedFlow<Int> = MutableSharedFlow()
    val uiMessageChannel = _uiMessageChannel.asSharedFlow()

    private val loadingStateMutable = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = loadingStateMutable

    fun getOffersTickets() {
        loadingStateMutable.value = true
        viewModelScope.launch {
            try {
                val offers = getOffersTicketsUseCase.execute()
                offersTicketsMutable.value = offers
            } catch (e: Exception) {
                _uiMessageChannel.emit(R.string.error)
            } finally {
                loadingStateMutable.value = false
            }
        }
    }
}