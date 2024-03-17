package com.walmart.countries.extensions

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * Initialize the SwipeRefreshLayout by setting the color scheme of swipe refresh spinner
 * and set the listener to be notified when a refresh is triggered via the swipe gesture.
 */
fun SwipeRefreshLayout.initialize(listener: SwipeRefreshLayout.OnRefreshListener) {
  setOnRefreshListener(listener)
  setColorSchemeResources(
    android.R.color.holo_blue_bright,
    android.R.color.holo_green_light,
    android.R.color.holo_orange_light,
    android.R.color.holo_red_light
  )
}

