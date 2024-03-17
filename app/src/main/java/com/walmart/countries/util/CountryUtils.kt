package com.walmart.countries.util

import com.walmart.countries.api.CountryListApi

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object CountryUtils {
  private const val BASE_URL = "https://gist.githubusercontent.com/"
  internal const val SWIPE_TO_REFRESH_DELAY = 500L

  fun getCountryListApi(): CountryListApi {
    val client: OkHttpClient = OkHttpClient.Builder().build()

    val retrofit: Retrofit = Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(client)
      .addConverterFactory(JacksonConverterFactory.create())
      .build()

    return retrofit.create(CountryListApi::class.java)
  }
}