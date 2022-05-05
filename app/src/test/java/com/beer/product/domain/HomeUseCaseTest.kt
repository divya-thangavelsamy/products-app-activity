package com.beer.product.domain

import com.beer.product.data.repository.ProductListRepositoryImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.reactivex.Single
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HomeUseCaseTest {

    private val repository = mockk<ProductListRepositoryImpl>()
    private val productListDomainModel = mockk<ProductListDomainModel>()
    private lateinit var useCase: HomeUseCase

    @Before
    fun setUp() {
        useCase = HomeUseCaseImpl(repository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `WHEN fetchProductList returns Success THEN valid domain model is returned`() {
        every {
            repository.fetchProductList()
        } returns Single.just(listOf(productListDomainModel))

        val result = useCase().test().values()[0]

        assertEquals(productListDomainModel, result[0])
    }

    @Test
    fun `WHEN fetchProductList returns error THEN valid error response is returned`() {
        val throwable = Throwable(TEST_ERROR_MESSAGE)
        every {
            repository.fetchProductList()
        } returns Single.error(throwable)

        val result = useCase().test().errors()[0]

        assertEquals(throwable, result)
    }

    private companion object {
        const val TEST_ERROR_MESSAGE = "error_message"
    }
}
