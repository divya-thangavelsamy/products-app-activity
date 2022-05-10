package com.beer.product.domain

import io.reactivex.Single

interface ProductListUseCase {
    operator fun invoke(): Single<List<ProductListDomainModel>>
}
