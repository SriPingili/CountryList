package com.walmart.countries.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.walmart.countries.databinding.FragmentCountryListBinding
import com.walmart.countries.extensions.initialize
import com.walmart.countries.util.CountryUtils.SWIPE_TO_REFRESH_DELAY
import com.walmart.countries.viewmodel.CountryListViewModel

class CountryListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
  private val viewModel: CountryListViewModel by viewModels()
  private lateinit var binding: FragmentCountryListBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentCountryListBinding.inflate(inflater).apply {
      lifecycleOwner = this@CountryListFragment
      uxContent = viewModel
      fragmentCountryListSwipeToRefresh.initialize(this@CountryListFragment)
    }

    return binding.root
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    // preserve the scroll position to handle the orientation changes
    val layoutManager: LinearLayoutManager? =
      binding.countryListRecyclerView.layoutManager as? LinearLayoutManager
    viewModel.scrollPosition = layoutManager?.findFirstCompletelyVisibleItemPosition() ?: 0
  }

  override fun onRefresh() {
    viewModel.getCountriesList()

    // hide swipe to refresh spinner after the set delay
    Handler(Looper.getMainLooper()).apply {
      postDelayed(
        {
          binding.fragmentCountryListSwipeToRefresh.isRefreshing = false
        },
        SWIPE_TO_REFRESH_DELAY
      )
    }
  }
}