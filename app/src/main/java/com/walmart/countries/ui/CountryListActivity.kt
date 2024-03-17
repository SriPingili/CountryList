package com.walmart.countries.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.walmart.countries.R.layout

class CountryListActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_country_list)
  }
}