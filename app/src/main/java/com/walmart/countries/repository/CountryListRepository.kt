package com.walmart.countries.repository

import com.walmart.countries.model.CountriesFetchResult

/**
 * A repository for fetching the country details, provides abstraction over the
 * internal implementation
 */
interface CountryListRepository {
  suspend fun getCountriesList(): CountriesFetchResult
}