package com.beer.product.data.di

import com.beer.product.data.mapper.DatabaseDetailsMapper
import com.beer.product.data.mapper.DatabaseListMapper
import com.beer.product.data.mapper.ProductDetailsMapper
import com.beer.product.data.mapper.ProductListMapper
import com.beer.product.data.repository.ProductDetailsRepository
import com.beer.product.data.repository.ProductListRepository
import dagger.Module
import dagger.Provides
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

    @Singleton
    @Provides
    fun provideListMapper(): ProductListMapper {
        return ProductListMapper()
    }

    @Singleton
    @Provides
    fun provideDetailsMapper(): ProductDetailsMapper {
        return ProductDetailsMapper()
    }

    @Singleton
    @Provides
    fun provideDatabaseListMapper(): DatabaseListMapper {
        return DatabaseListMapper()
    }

    @Singleton
    @Provides
    fun provideDatabaseDetailsMapper(): DatabaseDetailsMapper {
        return DatabaseDetailsMapper()
    }


}

