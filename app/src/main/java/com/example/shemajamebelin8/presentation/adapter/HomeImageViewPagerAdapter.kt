package com.example.shemajamebelin8.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shemajamebelin8.databinding.ItemHomeBinding
import com.example.shemajamebelin8.presentation.base.loadImage
import com.example.shemajamebelin8.presentation.model.CitiesUiModel


class HomeImageViewPagerAdapter :
    ListAdapter<CitiesUiModel, HomeImageViewPagerAdapter.HomeImageViewHolder>(
        DiffCallback
    ) {

    inner class HomeImageViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(city: CitiesUiModel) {
            with(binding) {
                imageViewCover.loadImage(city.cover)
                tvPrice.text = city.price
                tvPlace.text = city.location
                tvTitle.text = city.title
                ratingStars.rating = city.rate?.toFloat() ?: 0.0f
                tvReaction.text = city.reactionCount.toString()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeImageViewHolder {
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeImageViewHolder, position: Int) {
        val cities = getItem(position)
        holder.bind(cities)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CitiesUiModel>() {
        override fun areItemsTheSame(oldItem: CitiesUiModel, newItem: CitiesUiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CitiesUiModel, newItem: CitiesUiModel): Boolean {
            return oldItem == newItem
        }
    }
}