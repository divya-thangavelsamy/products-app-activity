package com.beer.product.data.di

import android.content.Context
import androidx.room.Room
import com.beer.product.data.database.ProductsDatabase
import com.beer.product.data.database.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): ProductsDatabase {
        return Room.databaseBuilder(
            appContext,
            ProductsDatabase::class.java,
            "Products"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideChannelDao(productsDatabase: ProductsDatabase): ProductDao {
        return productsDatabase.productDao
    }

}
