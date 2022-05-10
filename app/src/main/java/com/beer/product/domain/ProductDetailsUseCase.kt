package com.beer.product.domain

import io.reactivex.Single

interface ProductDetailsUseCase {
    operator fun invoke(id: Int): Single<ProductDetailsDomainModel>
}
