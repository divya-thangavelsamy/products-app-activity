package com.beer.product.data.repository

import com.beer.product.data.api.WebService
import com.beer.product.data.dto.Product
import javax.inject.Inject

class ProductListRepository @Inject constructor(
    private val webService: WebService,
) {

    suspend fun fetchProductList(): List<Product> {
      return webService.getProductList()
    }
}
