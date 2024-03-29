package com.walmart.countries.repository

import com.walmart.countries.api.CountryListApi
import com.walmart.countries.model.CountriesFetchResult
import com.walmart.countries.model.CountriesFetchResult.EmptyResponse
import com.walmart.countries.model.CountriesFetchResult.Failure
import com.walmart.countries.model.CountriesFetchResult.Success
import com.walmart.countries.model.Country
import com.walmart.countries.util.CountryUtils.getCountryListApi
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.MalformedURLException

class CountryListRepositoryImpl(private val api: CountryListApi = getCountryListApi()) :
  CountryListRepository {

  override suspend fun getCountriesList(): CountriesFetchResult {
    return try {
      val response: Response<List<Country>> = api.getCountriesList()

      if (response.isSuccessful) {
        val data: List<Country> = response.body() ?: return Failure.Unknown(response.message())

        if (data.isNotEmpty()) Success(data) else EmptyResponse
      } else {
        Failure.Unknown(response.message())
      }
    } catch (_: MalformedURLException) {
      Failure.Malformed
    } catch (_: IOException) {
      Failure.IOException
    } catch (_: HttpException) {
      Failure.HttpException
    } catch (exception: Exception) {
      Failure.Unknown(exception.message)
    }
  }
}