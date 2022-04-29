package com.beer.product.data.api

import com.beer.product.data.dto.Product
import com.beer.product.data.dto.ProductDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {

    @GET("beers")
    fun getProductList(): Single<List<Product>>

    @GET("beers/{id}")
    fun getProductDetails(@Path("id") id: Int): Single<List<ProductDetails>>
}
