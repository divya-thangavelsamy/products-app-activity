package com.beer.product.ui.productDetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.beer.product.RxImmediateSchedulerRule
import com.beer.product.data.repository.ResultOf
import com.beer.product.domain.*
import com.beer.product.ui.productList.ProductListViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.reactivex.Single
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductListViewModelTest {

    private val useCase = mockk<ProductListUseCase>()
    private val productListDomainModel = mockk<ProductListDomainModel>()
    private val mockLiveDataObserver = mockk<ResultOf<ProductListDomainModel>>()
    private lateinit var viewModel: ProductListViewModel

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val ruleForLivaData = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = ProductListViewModel(useCase)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `WHEN fetchProductDetails called THEN update livedata on Response`() {
        every {
            useCase.invoke()
        } returns Single.just(listOf(productListDomainModel))

        viewModel.productList.observeForever { mockLiveDataObserver }
        viewModel.fetchProductList()

        assertEquals(viewModel.productList.value, ResultOf.Success(listOf(productListDomainModel)))
    }

    @Test
    fun `WHEN fetchProductDetails called THEN update livedata on Failure`() {
        val throwable = Throwable(TEST_ERROR_MESSAGE)
        every {
            useCase.invoke()
        } returns Single.error(throwable)

        viewModel.productList.observeForever { mockLiveDataObserver }
        viewModel.fetchProductList()

        assertEquals(viewModel.productList.value, ResultOf.Failure(throwable))
    }

    private companion object {
        const val TEST_ERROR_MESSAGE = "error_message"
    }
}
