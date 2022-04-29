package com.beer.product.data.repository

import com.beer.product.data.api.WebService
import com.beer.product.data.dto.ProductDetailsResponse
import com.beer.product.data.mapper.ProductDetailsMapper
import io.reactivex.Single
import javax.inject.Inject

interface ProductDetailsRepository {
    fun fetchProductDetails(id: Int): Single<ProductDetailsResponse>
}

class ProductDetailsRepositoryImpl @Inject constructor(
    private val webService: WebService,
    private val productDetailsMapper: ProductDetailsMapper
) : ProductDetailsRepository {

    override fun fetchProductDetails(id: Int): Single<ProductDetailsResponse> {
        return webService.getProductDetails(id)
            .map {
                productDetailsMapper.map(it.first())
            }
    }
}
