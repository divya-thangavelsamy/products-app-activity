package com.beer.product.data.api

import com.beer.product.data.dto.ProductDetailsResponse
import com.beer.product.data.dto.ProductResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

interface WebService {

    @GET("beers")
    fun getProductList(): Single<List<ProductResponse>>

    @GET("beers/{id}")
    fun getProductDetails(@Path("id") id: Int): Single<List<ProductDetailsResponse>>
}









//interface WebService {
//    fun getProductList(): Single<List<ProductResponse>>
//
//    fun getProductDetails(@Path("id") id: Int): Single<List<ProductDetailsResponse>>
//
//}
//
//class WebServiceImpl @Inject constructor() : WebService {
//
//    @GET("beers")
//    override fun getProductList(): Single<List<ProductResponse>> {
//        return this.getProductList()
//    }
//
//    @GET("beers/{id}")
//    override fun getProductDetails(@Path("id") id: Int): Single<List<ProductDetailsResponse>> {
//        return this.getProductDetails(id)
//    }
//}

