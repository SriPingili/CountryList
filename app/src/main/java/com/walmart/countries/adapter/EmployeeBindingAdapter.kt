package com.walmart.countries.adapter

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.walmart.countries.R
import com.walmart.countries.viewmodel.UIStatus
import com.walmart.countries.viewmodel.UIStatus.Success

/**
 * BindingAdapter to set up the recycler view (gets called from fragment_country_list)
 *
 * @param view - the [RecyclerView]
 * @param uiStatus - the [UIStatus], represents the state of the network call
 * @param scrollPosition - the scrollTo position for the recycler view to handle the orientation changes
 */
@BindingAdapter("uxRecyclerViewUiStatus", "uxRecyclerViewScrollPosition")
fun setUpCountryListRecyclerView(
  view: RecyclerView,
  uiStatus: UIStatus,
  scrollPosition: Int
) {

  view.apply {
    visibility = if (uiStatus is Success) View.VISIBLE else View.GONE
    layoutManager = LinearLayoutManager(view.context)
    adapter = CountryListRecyclerViewAdapter().apply {
      differ.submitList((uiStatus as? Success)?.countries)
    }
    scrollToPosition(scrollPosition)
  }
}

/**
 * BindingAdapter to set up the text and visibility of the text view (which displays error message).
 * This will be called from [fragment_country_list.xml]
 *
 * @param view - the [TextView]
 * @param uiStatus - the [UIStatus], represents the state of the network call
 */
@BindingAdapter("uxTextViewUiStatus")
fun setUpCountryListTextView(
  view: TextView,
  uiStatus: UIStatus,
) {

  view.apply {
    text = when (uiStatus) {
      is UIStatus.SuccessNoResults -> context.getString(R.string.empty_response_error_message)
      is UIStatus.Error -> context.getString(R.string.general_error_message)
      else -> ""
    }

    visibility =
      if (uiStatus == UIStatus.Error || uiStatus == UIStatus.SuccessNoResults) View.VISIBLE
      else View.GONE
  }
}

