package com.beer.product.data.mapper

import com.beer.product.data.database.entity.DatabaseProductListItem
import com.beer.product.data.dto.Product

class ProductListMapper {

    fun map(item : List<DatabaseProductListItem>) : List<Product> {
        return item.map {
            Product(
                id = it.id,
                name = it.name,
                tagline = it.tagline,
                image_url = it.image,
                ibu = it.ibu,
                abv = it.abv
            )
        }
    }
}
