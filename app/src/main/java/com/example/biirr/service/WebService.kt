package com.example.biirr.service

import com.example.biirr.model.Product
import com.example.biirr.model.ProductDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    @GET("beers")
    fun getBeerInfo() : Single<List<Product>>

    @GET("beers")
    fun getSearchedProduct(@Query("beer_name") beerName: String) : Single<List<Product>>

    @GET("beers/{id}")
    fun getProductDetails(@Path("id") id: Int): Single<List<ProductDetails>>
}