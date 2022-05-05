package com.beer.product.ui.productDetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.beer.product.RxImmediateSchedulerRule
import com.beer.product.data.repository.ResultOf
import com.beer.product.domain.DetailsUseCaseImpl
import com.beer.product.domain.DetailsUseCaseTest
import com.beer.product.domain.ProductDetailsDomainModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.reactivex.Single
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductDetailsViewModelTest {

    private val useCase = mockk<DetailsUseCaseImpl>()
    private val productDetailsDomainModel = mockk<ProductDetailsDomainModel>()
    private val mockLiveDataObserver = mockk<ResultOf<ProductDetailsDomainModel>>()
    private lateinit var viewModel: ProductDetailsViewModel

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val ruleForLivaData = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = ProductDetailsViewModel(useCase)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `WHEN fetchProductDetails called THEN update livedata on Response`() {
        every {
            useCase.invoke(2)
        } returns Single.just(productDetailsDomainModel)


        viewModel.productDetails.observeForever { mockLiveDataObserver }
        viewModel.fetchProductDetails(2)

        assertEquals(viewModel.productDetails.value, ResultOf.Success(productDetailsDomainModel))
    }

    @Test
    fun `WHEN fetchProductDetails called THEN update livedata on Failure`() {
        val throwable = Throwable(TEST_ERROR_MESSAGE)
        every {
            useCase.invoke(2)
        } returns Single.error(throwable)


        viewModel.productDetails.observeForever { mockLiveDataObserver }
        viewModel.fetchProductDetails(2)

        assertEquals(viewModel.productDetails.value, ResultOf.Failure(throwable))
    }

    private companion object {
        const val TEST_ERROR_MESSAGE = "error_message"
    }
}
