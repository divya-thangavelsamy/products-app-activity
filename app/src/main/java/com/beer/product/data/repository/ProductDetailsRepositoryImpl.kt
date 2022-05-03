package com.beer.product.data.repository

import com.beer.product.data.api.WebService
import com.beer.product.data.mapper.ProductDetailsMapper
import com.beer.product.domain.ProductDetailsDomainModel
import com.beer.product.domain.ProductDetailsRepository
import io.reactivex.Single
import javax.inject.Inject

class ProductDetailsRepositoryImpl @Inject constructor(
    private val webService: WebService,
    private val productDetailsMapper: ProductDetailsMapper
) : ProductDetailsRepository {

    override fun fetchProductDetails(id: Int): Single<ProductDetailsDomainModel> {
        return webService.getProductDetails(id)
            .map {
                productDetailsMapper.map(it.first())
            }
    }
}
