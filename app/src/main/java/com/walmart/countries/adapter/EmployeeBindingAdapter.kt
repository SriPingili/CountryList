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
 */
@BindingAdapter("uxRecyclerViewUiStatus")
fun setUpCountryListRecyclerView(
  view: RecyclerView,
  uiStatus: UIStatus,
) {
  val uiStatusSuccess: Success = uiStatus as? Success ?: return

  view.apply {
    layoutManager = LinearLayoutManager(view.context)
    adapter = CountryListRecyclerViewAdapter().apply {
      differ.submitList(uiStatusSuccess.countries)
    }
    visibility = View.VISIBLE
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
fun setVisibilityTextView(
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

