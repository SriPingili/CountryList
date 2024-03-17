package com.walmart.countries.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.walmart.countries.databinding.FragmentCountryListBinding
import com.walmart.countries.viewmodel.CountryListViewModel

class CountryListFragment : Fragment() {
  private val viewModel: CountryListViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val binding = FragmentCountryListBinding.inflate(inflater).apply {
      lifecycleOwner = this@CountryListFragment
      uxContent = viewModel
    }

    return binding.root
  }
}