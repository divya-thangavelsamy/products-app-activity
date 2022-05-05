package com.beer.product.domain

import io.reactivex.Single
import javax.inject.Inject

class ProductDetailsUseCaseImpl @Inject constructor(private val repository: ProductDetailsRepository) :
    ProductDetailsUseCase {

    override operator fun invoke(id: Int): Single<ProductDetailsDomainModel> =
        repository.fetchProductDetails(id)
}
