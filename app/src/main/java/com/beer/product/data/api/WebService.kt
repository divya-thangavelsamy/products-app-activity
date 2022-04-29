package com.beer.product.data.api

import com.beer.product.data.dto.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {

    @GET("beers")
    suspend fun getProductList(): List<Product>

    @GET("beers/{id}")
    suspend fun getProductDetails(@Path("id") id: Int): List<Product>
}
