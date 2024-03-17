package com.walmart.countries.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.walmart.countries.model.CountriesFetchResult
import com.walmart.countries.model.Country
import com.walmart.countries.repository.CountryListRepository
import com.walmart.countries.testUtils.MainCoroutineScopeRule
import com.walmart.countries.testUtils.getOrAwaitValue
import com.walmart.countries.viewmodel.UIStatus.Success
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.stub

@RunWith(MockitoJUnitRunner::class)
class CountryListViewModelTest {

  private lateinit var viewModel: CountryListViewModel

  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  @ExperimentalCoroutinesApi
  @get:Rule
  var mainCoroutineRule = MainCoroutineScopeRule()

  @Mock
  private lateinit var repository: CountryListRepository

  @Mock
  private lateinit var context: Context

  @Before
  fun setup() {
    viewModel = CountryListViewModel(repository)
  }

  @Test
  fun `test getCountriesList with a successful non empty response`() {
    val country = Country(
      capital = "Washington DC", code = "US", name = "United States of America", region = "NA"
    )
    val countryResponse = listOf<Country>(country)

    repository.stub {
      onBlocking { getCountriesList() } doAnswer {
        CountriesFetchResult.Success(countryResponse)
      }
    }

    viewModel.getCountriesList()

    val result: Success? = viewModel.uiStatus.getOrAwaitValue() as? Success
    assertThat(result, `is`(notNullValue()))
    assertThat(result?.countries?.isNotEmpty(), `is`(true))
    assertThat(result?.countries?.size, `is`(1))
    assertThat(result?.countries?.get(0), `is`(country))
  }

  @Test
  fun `test getCountriesList with empty response`() {
    repository.stub {
      onBlocking { getCountriesList() } doAnswer {
        CountriesFetchResult.EmptyResponse
      }
    }

    viewModel.getCountriesList()

    assertTrue(viewModel.uiStatus.getOrAwaitValue() is UIStatus.SuccessNoResults)
  }

  @Test
  fun `test getCountriesList with failure scenario`() {
    repository.stub {
      onBlocking { getCountriesList() } doAnswer {
        CountriesFetchResult.Failure.HttpException
      }
    }

    viewModel.getCountriesList()

    assertTrue(viewModel.uiStatus.getOrAwaitValue() is UIStatus.Error)
  }
}