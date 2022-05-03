package com.beer.product.data.mapper

import com.beer.product.data.dto.ProductResponse
import com.beer.product.domain.ProductListDomainModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProductListMapperTest {

    private val productListDomainModel = mockk<ProductListDomainModel>()
    private val productResponse = mockk<ProductResponse>()
    private lateinit var mapper : ProductListMapper

    @Before
    fun setUp() {
        mapper = ProductListMapper()
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `WHEN mapper maps response and THEN returns valid domain model`() {
        every { productResponse.id } returns PRODUCT_ID
        every { productResponse.name } returns PRODUCT_NAME
        every { productResponse.tagline } returns PRODUCT_TAG_LINE
        every { productResponse.image_url } returns PRODUCT_IMAGE_URL

        every { productListDomainModel.id } returns PRODUCT_ID
        every { productListDomainModel.name } returns PRODUCT_NAME
        every { productListDomainModel.tagline } returns PRODUCT_TAG_LINE
        every { productListDomainModel.imageUrl } returns PRODUCT_IMAGE_URL

        val result = mapper.map(listOf(productResponse))

        Assert.assertEquals(productListDomainModel.id, result[0].id)
        Assert.assertEquals(productListDomainModel.name, result[0].name)
        Assert.assertEquals(productListDomainModel.tagline, result[0].tagline)
        Assert.assertEquals(productListDomainModel.imageUrl, result[0].imageUrl)
    }

    private companion object {
        const val PRODUCT_ID = 1
        const val PRODUCT_NAME = "test"
        const val PRODUCT_TAG_LINE = "tagline"
        const val PRODUCT_IMAGE_URL = "image_url"
    }
}
