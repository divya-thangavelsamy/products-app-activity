package com.beer.product.data.repository

import com.beer.product.data.api.WebService
import com.beer.product.data.dto.ProductResponse
import com.beer.product.data.mapper.ProductListMapper
import com.beer.product.domain.ProductListDomainModel
import com.beer.product.domain.ProductListRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.reactivex.Single
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProductListRepositoryTest {

    private val apiService = mockk<WebService>()
    private val productListResponse = mockk<ProductResponse>()
    private val productListDomainModel = mockk<ProductListDomainModel>()
    private val productListMapper = mockk<ProductListMapper>()
    private lateinit var repository: ProductListRepository

    @Before
    fun setUp() {
        repository = ProductListRepositoryImpl(apiService, productListMapper)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `WHEN fetchProductList returns Success THEN valid domain model is returned`() {
        every {
            apiService.getProductList()
        } returns Single.just(listOf(productListResponse))
        every { productListMapper.map(listOf(productListResponse)) } returns listOf(
            productListDomainModel
        )

        val result = repository.fetchProductList().test().values()[0]

        Assert.assertEquals(productListDomainModel, result[0])
    }

    @Test
    fun `WHEN fetchProductList returns Error THEN valid error response is returned`() {
        val throwable = Throwable(TEST_ERROR_MESSAGE)
        every {
            apiService.getProductList()
        } returns Single.error(throwable)
        every { productListMapper.map(listOf(productListResponse)) } returns listOf(
            productListDomainModel
        )

        val result = repository.fetchProductList().test().errors()[0]

        Assert.assertEquals(throwable, result)
    }

    private companion object {
        const val TEST_ERROR_MESSAGE = "error_message"
    }
}
