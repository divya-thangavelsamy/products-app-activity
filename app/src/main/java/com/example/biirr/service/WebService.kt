package com.example.biirr.service

import com.example.biirr.model.Product
import com.example.biirr.model.ProductDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {

    @GET("beers")
    fun getBeerInfo() : Single<List<Product>>

    @GET("beers/{id}")
    fun getProductDetails(@Path("id") id: Int): Single<List<ProductDetails>>
}