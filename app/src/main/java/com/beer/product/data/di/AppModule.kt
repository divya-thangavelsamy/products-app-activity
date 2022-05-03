package com.beer.product.data.di

import android.content.Context
import com.beer.product.data.api.WebService
import com.beer.product.data.mapper.ProductListMapper
import com.beer.product.data.repository.ProductListRepositoryImpl
import com.beer.product.domain.HomeUseCase
import com.beer.product.domain.HomeUseCaseImpl
import com.beer.product.domain.ProductListRepository
import com.beer.product.utils.Constants
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit {
        return Retrofit
            .Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL).build()
    }

    @Provides
    @Singleton
    fun provideWebservice(retrofit: Retrofit): WebService {
        return retrofit.create(WebService::class.java)
    }
}
