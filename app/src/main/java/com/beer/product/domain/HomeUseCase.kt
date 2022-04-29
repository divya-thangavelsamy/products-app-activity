package com.beer.product.domain

import com.beer.product.data.repository.ProductListRepository
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val repository: ProductListRepository) {

    suspend operator fun invoke() = repository.fetchProductList()

}
