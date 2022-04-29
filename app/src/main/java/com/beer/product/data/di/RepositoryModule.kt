package com.beer.product.data.di

import com.beer.product.data.repository.ProductDetailsRepository
import com.beer.product.data.repository.ProductListRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    fun provideListRepository(repository: ProductListRepository): ProductListRepository {
        return repository
    }

    @Singleton
    fun provideDetailsRepository(repository: ProductDetailsRepository): ProductDetailsRepository {
        return repository
    }
}

