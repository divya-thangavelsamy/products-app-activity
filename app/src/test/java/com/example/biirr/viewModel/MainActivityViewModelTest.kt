package com.example.biirr.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.biirr.model.Product
import com.example.biirr.service.WebService
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify


class MainActivityViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var webservice: WebService

    @Mock
    private lateinit var mockBeerData: List<Product>

    @Mock
    private lateinit var viewModel: MainActivityViewModel

    @Captor
    var acLong: ArgumentCaptor<*>? = null

    var acString: ArgumentCaptor<*> = ArgumentCaptor.forClass(
        String::class.java
    )


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

    @Test
    fun `when load beers called verify getInfo called` () {

        verify(viewModel).testmethod(acString.capture() as String)
        assertTrue("buzz" == acString?.value)
    }
}