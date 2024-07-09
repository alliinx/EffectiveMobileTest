package com.allinx.testtaskeffectivemobile.di

import com.allinx.domain.repository.ITicketsRepository
import com.allinx.domain.usecases.GetOffersTicketsUseCase
import com.allinx.domain.usecases.GetOffersWithImagesUseCase
import com.allinx.domain.usecases.GetSavedCityFromUseCase
import com.allinx.domain.usecases.GetTicketsUseCase
import com.allinx.domain.usecases.SaveCityFromUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideGetOffersWithImagesUseCase(repository: ITicketsRepository): GetOffersWithImagesUseCase {
        return GetOffersWithImagesUseCase(repository)
    }

    @Provides
    fun provideGetSavedCityFromUseCase(repository: ITicketsRepository): GetSavedCityFromUseCase {
        return GetSavedCityFromUseCase(repository)
    }

    @Provides
    fun provideSaveCityFromUseCase(repository: ITicketsRepository): SaveCityFromUseCase {
        return SaveCityFromUseCase(repository)
    }

    @Provides
    fun provideGetOffersTicketsUseCase(repository: ITicketsRepository): GetOffersTicketsUseCase {
        return GetOffersTicketsUseCase(repository)
    }

    @Provides
    fun provideGetTicketsUseCase(repository: ITicketsRepository): GetTicketsUseCase {
        return GetTicketsUseCase(repository)
    }
}