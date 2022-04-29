package com.beer.product.data.mapper

import com.beer.product.data.database.entity.DatabaseProductDetails
import com.beer.product.data.dto.Product

class DatabaseDetailsMapper {

    fun map(item: Product) : DatabaseProductDetails {
        return DatabaseProductDetails(
            id = item.id,
            name = item.name,
            tagline = item.tagline,
            image = item.image_url,
            ibu = item.ibu,
            abv = item.abv,
        )

    }
}
