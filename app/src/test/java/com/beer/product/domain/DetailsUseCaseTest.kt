package com.beer.product.domain

import com.beer.product.data.repository.ProductDetailsRepositoryImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.reactivex.Single
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DetailsUseCaseTest {

    private val repository = mockk<ProductDetailsRepositoryImpl>()
    private val productDetailsDomainModel = mockk<ProductDetailsDomainModel>()
    private lateinit var useCase: DetailsUseCase

    @Before
    fun setUp() {
        useCase = DetailsUseCaseImpl(repository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `WHEN fetchProductDetails returns Success THEN valid domain model is returned`() {
        every {
            repository.fetchProductDetails(2)
        } returns Single.just(productDetailsDomainModel)

        val result = useCase(2).test().values()[0]

        Assert.assertEquals(productDetailsDomainModel, result)
    }

    @Test
    fun `WHEN fetchProductDetails returns error THEN valid error response is returned`() {
        val throwable = Throwable(TEST_ERROR_MESSAGE)
        every {
            repository.fetchProductDetails(2)
        } returns Single.error(throwable)

        val result = useCase(2).test().errors()[0]

        Assert.assertEquals(throwable, result)
    }

    private companion object {
        const val TEST_ERROR_MESSAGE = "error_message"
    }
}
