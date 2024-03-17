package com.walmart.countries.model

sealed interface CountriesFetchResult {
  data class Success(
    val countries: List<Country>
  ) : CountriesFetchResult

  // TODO this section can be expanded to handle more errors gracefully or pass in the respective
  // error strings to be displayed to the user
  interface Failure : CountriesFetchResult {
    data object Malformed : Failure
    data object IOException : Failure
    data object HttpException : Failure
    data class Unknown(
      val cause: String?
    ) : Failure
  }

  data object EmptyResponse : CountriesFetchResult
}