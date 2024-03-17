package com.walmart.countries.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walmart.countries.model.CountriesFetchResult
import com.walmart.countries.model.CountriesFetchResult.EmptyResponse
import com.walmart.countries.model.CountriesFetchResult.Success
import com.walmart.countries.model.Country
import com.walmart.countries.repository.CountryListRepository
import com.walmart.countries.repository.CountryListRepositoryImpl
import kotlinx.coroutines.launch

class CountryListViewModel(
  private val repository: CountryListRepository = CountryListRepositoryImpl()
) : ViewModel() {

  private val _uiStatus = MutableLiveData<UIStatus>()
  val uiStatus: LiveData<UIStatus> = _uiStatus

  var scrollPosition: Int = 0

  init {
    getCountriesList()
  }

  internal fun getCountriesList() {
    _uiStatus.value = UIStatus.Loading

    viewModelScope.launch {
      when (val response: CountriesFetchResult = repository.getCountriesList()) {
        is Success -> _uiStatus.value = UIStatus.Success(response.countries)
        is EmptyResponse -> _uiStatus.value = UIStatus.SuccessNoResults
        else -> {
          // for the scope of this coding challenge I'm mapping all the errors into UIStatus.Error
          // ideally we could handle the errors appropriately (eg: different error messages)
          _uiStatus.value = UIStatus.Error
        }
      }
    }
  }
}

sealed interface UIStatus {
  data object Loading : UIStatus
  data class Success(
    val countries: List<Country>
  ) : UIStatus

  data object SuccessNoResults : UIStatus
  data object Error : UIStatus
}