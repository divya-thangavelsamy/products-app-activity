//package com.beer.product.ui.productList
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import com.beer.product.domain.HomeUseCase
//import io.mockk.every
//import io.mockk.mockk
//import io.mockk.verify
//import kotlinx.coroutines.Dispatchers
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.junit.runners.JUnit4
//import org.mockito.kotlin.any
//
//@RunWith(JUnit4::class)
//class HomeViewModelTest {
//
//    @get:Rule
//    var instantExecutorRule = InstantTaskExecutorRule()
//
//    val useCase = mockk<HomeUseCase>()
//    private lateinit var homeViewModel: HomeViewModel
//    val dispatcher = Dispatchers.Unconfined
//
//    @Before
//    fun setup() {
//        homeViewModel = HomeViewModel(useCase)
//    }
//
//    @Test
//    fun homeUseCase_returns_value() {
//        //Arrange
//        every { useCase.data } returns any()
//        //Act
//        homeViewModel.productList
//        //Assert
//        verify { homeUseCase_returns_value() }
//    }
//}
