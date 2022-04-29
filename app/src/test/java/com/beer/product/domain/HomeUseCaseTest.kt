package com.beer.product.domain

import com.beer.product.data.dto.Product
import com.beer.product.data.repository.ProductListRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test

class HomeUseCaseTest {

    val repository = mockk<ProductListRepository>()

    val product = mockk<Product>()

    val useCase = HomeUseCase(repository)

    @Test
    fun getData() {
    }

    @Test
    operator fun invoke() {
        runBlocking {
            useCase.invoke()
        }

        coVerify { repository.refreshProductList() }
    }
}
