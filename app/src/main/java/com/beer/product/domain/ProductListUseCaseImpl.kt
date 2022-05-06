package com.beer.product.domain

import io.reactivex.Single
import javax.inject.Inject

class ProductListUseCaseImpl @Inject constructor(private val repository: ProductListRepository) :
    ProductListUseCase {

    override operator fun invoke(): Single<List<ProductListDomainModel>> =
        repository.fetchProductList()
}
