package com.walmart.countries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.walmart.countries.R.string
import com.walmart.countries.databinding.CountryListItemViewBinding
import com.walmart.countries.model.Country

/**
 * Adapter used by the recycler view to display list of [Country]
 */
class CountryListRecyclerViewAdapter :
  RecyclerView.Adapter<CountryListRecyclerViewAdapter.CountryListViewHolder>() {

  private val differCallback = object : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(
      oldItem: Country,
      newItem: Country
    ): Boolean {
      return oldItem.code == newItem.code
    }

    override fun areContentsTheSame(
      oldItem: Country,
      newItem: Country
    ): Boolean {
      return oldItem == newItem
    }
  }

  /**
   * Helper for computing the difference between two lists via [DiffUtil] on a background thread.
   *
   * https://developer.android.com/reference/androidx/recyclerview/widget/AsyncListDiffer
   */
  val differ = AsyncListDiffer(this, differCallback)

  inner class CountryListViewHolder(private val binding: CountryListItemViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(country: Country) {

      binding.apply {
        countryListItemViewCountryNameAndRegion.apply {
          text = context.getString(string.country_name_and_region, country.name, country.region)
        }
        countryListItemViewCountryCode.text = country.code
        countryListItemViewCountryCapital.text = country.capital
      }
    }
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): CountryListViewHolder {
    val binding =
      CountryListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    return CountryListViewHolder(binding)
  }

  override fun onBindViewHolder(
    holder: CountryListViewHolder,
    position: Int
  ) {
    val country: Country = differ.currentList[position]

    holder.bind(country)
  }

  override fun getItemCount() = differ.currentList.size
}