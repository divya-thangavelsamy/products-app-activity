package com.beer.product.data.api

import com.beer.product.data.dto.ProductDetailsResponse
import com.beer.product.data.dto.ProductResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {

    @GET("beers")
    fun getProductList(): Single<List<ProductResponse>>

    @GET("beers/{id}")
    fun getProductDetails(@Path("id") id: Int): Single<List<ProductDetailsResponse>>

}
