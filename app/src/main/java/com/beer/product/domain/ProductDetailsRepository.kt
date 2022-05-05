package com.beer.product.domain

import io.reactivex.Single

interface ProductDetailsRepository {
    fun fetchProductDetails(id: Int): Single<ProductDetailsDomainModel>
}
