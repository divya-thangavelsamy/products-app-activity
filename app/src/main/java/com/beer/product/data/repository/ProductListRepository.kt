package com.beer.product.data.repository

import com.beer.product.data.api.WebService
import com.beer.product.data.dto.ProductListResponse
import com.beer.product.data.mapper.ProductListMapper
import io.reactivex.Single
import javax.inject.Inject

interface ProductListRepository {
    fun fetchProductList() : Single<List<ProductListResponse>>
}

class ProductListRepositoryImpl @Inject constructor(
    private val webService: WebService,
    private val productListMapper: ProductListMapper
) : ProductListRepository {
     override fun fetchProductList(): Single<List<ProductListResponse>> {
        return webService.getProductList()
            .map {
                productListMapper.map(it)
            }
     }
}
