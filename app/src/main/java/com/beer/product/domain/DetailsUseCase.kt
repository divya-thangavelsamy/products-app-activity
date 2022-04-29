package com.beer.product.domain

import com.beer.product.data.repository.ProductDetailsRepository
import javax.inject.Inject

class DetailsUseCase @Inject constructor(private val repository: ProductDetailsRepository) {

    suspend operator fun invoke(id: Int) = repository.fetchProductDetails(id)
}
