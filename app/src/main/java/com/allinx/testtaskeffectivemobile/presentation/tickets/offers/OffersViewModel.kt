package com.allinx.testtaskeffectivemobile.presentation.tickets.offers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allinx.domain.models.offer.OfferWithImage
import com.allinx.domain.usecases.GetOffersWithImagesUseCase
import com.allinx.domain.usecases.GetSavedCityFromUseCase
import com.allinx.domain.usecases.SaveCityFromUseCase
import com.allinx.testtaskeffectivemobile.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OffersViewModel @Inject constructor(
    private val getOffersWithImagesUseCase: GetOffersWithImagesUseCase,
    private val getSavedCityFromUseCase: GetSavedCityFromUseCase,
    private val saveCityFromUseCase: SaveCityFromUseCase
) : ViewModel() {

    private val offersMutable = MutableLiveData<List<OfferWithImage>>()
    val resultOffers: LiveData<List<OfferWithImage>> = offersMutable

    private val savedCityMutable = MutableLiveData<String?>()
    val resultSavedCity: LiveData<String?> = savedCityMutable

    private val _uiMessageChannel: MutableSharedFlow<Int> = MutableSharedFlow()
    val uiMessageChannel = _uiMessageChannel.asSharedFlow()

    private val loadingStateMutable = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = loadingStateMutable

    fun getOffers() {
        loadingStateMutable.value = true
        viewModelScope.launch {
            try {
                val offers = getOffersWithImagesUseCase.execute()
                offersMutable.value = offers
            } catch (e: Exception) {
                _uiMessageChannel.emit(R.string.error)
            } finally {
                loadingStateMutable.value = false
            }
        }
    }

    fun getSavedCity() {
        loadingStateMutable.value = true
        viewModelScope.launch {
            try {
                val savedCity = getSavedCityFromUseCase.execute()
                savedCityMutable.value = savedCity
            } catch (e: Exception) {
                _uiMessageChannel.emit(R.string.error)
            } finally {
                loadingStateMutable.value = false
            }
        }
    }

    fun saveCity(city: String) {
        loadingStateMutable.value = true
        viewModelScope.launch {
            try {
                saveCityFromUseCase.execute(city)
            } catch (e: Exception) {
                _uiMessageChannel.emit(R.string.error)
            } finally {
                loadingStateMutable.value = false
            }
        }
    }
}