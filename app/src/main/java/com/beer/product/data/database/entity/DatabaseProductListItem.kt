package com.beer.product.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beer.product.data.dto.Product

@Entity
data class DatabaseProductListItem constructor(
    @PrimaryKey
    val id: Int,
    val name: String,
    val tagline: String,
    val image: String,
    val ibu: Double,
    val abv: Double
)
