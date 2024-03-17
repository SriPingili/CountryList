package com.walmart.countries.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * data class to store the Countries response
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class Country(
  @JsonProperty("capital") val capital: String,
  @JsonProperty("code") val code: String,
  @JsonProperty("name") val name: String,
  @JsonProperty("region") val region: String,
)