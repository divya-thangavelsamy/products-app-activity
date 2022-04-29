package com.beer.product.data.mapper

import com.beer.product.data.database.entity.DatabaseProductListItem
import com.beer.product.data.dto.Product

class DatabaseListMapper {

    fun map(item: List<Product>) : List<DatabaseProductListItem> {
        return item.map {
            DatabaseProductListItem(
                id =  it.id,
                name = it.name,
                tagline = it.tagline,
                ibu = it.ibu,
                abv = it.abv,
                image = it.image_url
            )
        }
    }
}
