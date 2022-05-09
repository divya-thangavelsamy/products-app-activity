package com.beer.product.data.repository

import com.beer.product.data.api.WebService
import com.beer.product.data.mapper.ProductListMapper
import com.beer.product.domain.ProductListDomainModel
import com.beer.product.domain.ProductListRepository
import io.reactivex.Single
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(
    private val webService: WebService,
    private val productListMapper: ProductListMapper
) : ProductListRepository {
    override fun fetchProductList(): Single<List<ProductListDomainModel>> {
        return webService.getProductList()
            .map {
                productListMapper.map(it)
            }
    }
}
