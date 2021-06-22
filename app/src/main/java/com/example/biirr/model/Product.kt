package com.example.biirr.model

data class Product(
    val id: Long,
    val name: String,
    val tagline: String,
    val image_url: String,
    val volume: Volume
)

data class Volume(
    val value: Int
)

data class ProductDetails(
    val name: String,
    val tagline: String,
    val image_url: String,
    val ibu: Double?,
    val abv: Double?
)


