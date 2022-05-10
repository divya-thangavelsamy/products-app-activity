package com.beer.product.data.repository

import com.beer.product.data.api.WebService
import com.beer.product.data.dto.ProductDetailsResponse
import com.beer.product.data.mapper.ProductDetailsMapper
import com.beer.product.domain.ProductDetailsDomainModel
import com.beer.product.domain.ProductDetailsRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.reactivex.Single
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProductDetailsRepositoryTest {

    private val apiService = mockk<WebService>()
    private val productDetailsResponse = mockk<ProductDetailsResponse>()
    private val productDetailsDomainModel = mockk<ProductDetailsDomainModel>()
    private val productDetailsMapper = mockk<ProductDetailsMapper>()
    private lateinit var repository: ProductDetailsRepository

    @Before
    fun setUp() {
        repository = ProductDetailsRepositoryImpl(apiService, productDetailsMapper)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `WHEN fetchProductList returns Success THEN valid domain model is returned`() {
        every {
            apiService.getProductDetails(2)
        } returns Single.just(listOf(productDetailsResponse))
        every { productDetailsMapper.map(productDetailsResponse) } returns
                productDetailsDomainModel

        val result = repository.fetchProductDetails(2).test().values()[0]

        Assert.assertEquals(productDetailsDomainModel, result)
    }

    @Test
    fun `WHEN fetchProductList returns Error THEN valid error response is returned`() {
        val throwable = Throwable(TEST_ERROR_MESSAGE)
        every {
            apiService.getProductDetails(2)
        } returns Single.error(throwable)
        every { productDetailsMapper.map(productDetailsResponse) } returns
                productDetailsDomainModel

        val result = repository.fetchProductDetails(2).test().errors()[0]

        Assert.assertEquals(throwable, result)
    }

    private companion object {
        const val TEST_ERROR_MESSAGE = "error_message"
    }
}
