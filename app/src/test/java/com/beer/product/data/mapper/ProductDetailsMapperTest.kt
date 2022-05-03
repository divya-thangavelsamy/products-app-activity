package com.beer.product.data.mapper

import com.beer.product.data.dto.ProductDetailsResponse
import com.beer.product.domain.ProductDetailsDomainModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProductDetailsMapperTest {

    private val productDetailsDomainModel = mockk<ProductDetailsDomainModel>()
    private val productDetailsResponse = mockk<ProductDetailsResponse>()
    private lateinit var mapper: ProductDetailsMapper

    @Before
    fun setUp() {
        mapper = ProductDetailsMapper()
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `WHEN mapper maps response and THEN returns valid domain model`() {
        every { productDetailsResponse.id } returns PRODUCT_ID
        every { productDetailsResponse.name } returns PRODUCT_NAME
        every { productDetailsResponse.tagline } returns PRODUCT_TAG_LINE
        every { productDetailsResponse.image_url } returns PRODUCT_IMAGE_URL
        every { productDetailsResponse.ibu } returns PRODUCT_IBU
        every { productDetailsResponse.abv } returns PRODUCT_ABV

        every { productDetailsDomainModel.id } returns PRODUCT_ID
        every { productDetailsDomainModel.name } returns PRODUCT_NAME
        every { productDetailsDomainModel.tagline } returns PRODUCT_TAG_LINE
        every { productDetailsDomainModel.imageUrl } returns PRODUCT_IMAGE_URL
        every { productDetailsDomainModel.ibu } returns PRODUCT_IBU
        every { productDetailsDomainModel.abv } returns PRODUCT_ABV

        val result = mapper.map(productDetailsResponse)

        Assert.assertEquals(productDetailsDomainModel.id, result.id)
        Assert.assertEquals(productDetailsDomainModel.name, result.name)
        Assert.assertEquals(productDetailsDomainModel.tagline, result.tagline)
        Assert.assertEquals(productDetailsDomainModel.imageUrl, result.imageUrl)
    }

    private companion object {
        const val PRODUCT_ID = 1
        const val PRODUCT_NAME = "test"
        const val PRODUCT_TAG_LINE = "tagline"
        const val PRODUCT_IMAGE_URL = "image_url"
        const val PRODUCT_IBU = 20.00
        const val PRODUCT_ABV = 20.00
    }
}

