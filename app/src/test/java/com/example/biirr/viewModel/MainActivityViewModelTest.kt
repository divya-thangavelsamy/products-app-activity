package com.example.biirr.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.biirr.model.Product
import com.example.biirr.service.WebService
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainActivityViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var webservice: WebService

    @Mock
    private lateinit var mockBeerData: List<Product>

    private lateinit var viewModel: MainActivityViewModel

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        viewModel = MainActivityViewModel(webservice)
    }

    @Test
    fun `when product data retrieved, set beerData values`() {

        viewModel.setProduct(mockBeerData)
        assertEquals(viewModel.beerData.value, mockBeerData)
    }
}