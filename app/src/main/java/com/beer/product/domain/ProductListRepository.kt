package com.beer.product.domain

import io.reactivex.Single

interface ProductListRepository {
    fun fetchProductList() : Single<List<ProductListDomainModel>>
}
