package com.walmart.countries.model

sealed interface CountriesFetchResult {
  data class Success(
    val response: CountriesResponse
  ) : CountriesFetchResult

  // this section can be expanded to handle more errors gracefully
  interface Failure : CountriesFetchResult {
    data object Malformed : Failure
    data object IOException : Failure
    data object HttpException : Failure
    data class Unknown(
      val cause: String?
    ) : Failure
  }

  data object Empty : CountriesFetchResult
}