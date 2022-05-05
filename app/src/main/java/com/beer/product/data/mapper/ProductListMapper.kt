package com.beer.product.data.mapper

import com.beer.product.data.dto.ProductResponse
import com.beer.product.domain.ProductListDomainModel
import javax.inject.Inject

class ProductListMapper @Inject constructor() {
    fun map(item: List<ProductResponse>): List<ProductListDomainModel> {
        return item.map {
            with(it) {
                ProductListDomainModel(
                    id = id,
                    name = name,
                    tagline = tagline,
                    imageUrl = image_url
                )
            }
        }
    }
}
