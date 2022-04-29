package com.beer.product.data.repository

import com.beer.product.data.api.WebService
import com.beer.product.data.dto.Product
import javax.inject.Inject

class ProductDetailsRepository @Inject constructor(
    private val webService: WebService,
) {
    suspend fun fetchProductDetails(id: Int): Product {
            return webService.getProductDetails(id).first()
        }
    }
