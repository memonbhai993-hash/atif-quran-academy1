package com.quranacademy.atifquranapp.ui.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.quranacademy.atifquranapp.databinding.ItemFeatureBinding
import com.quranacademy.atifquranapp.ui.home.FeatureItem

/**
 * Adapter for displaying features in Home fragment
 */
class FeatureAdapter : ListAdapter<FeatureItem, FeatureAdapter.FeatureViewHolder>(FeatureDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        val binding = ItemFeatureBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FeatureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FeatureViewHolder(
        private val binding: ItemFeatureBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FeatureItem) {
            binding.imgFeature.setImageResource(item.iconResId)
            binding.txtFeature.text = item.title
        }
    }

    private class FeatureDiffCallback : DiffUtil.ItemCallback<FeatureItem>() {
        override fun areItemsTheSame(oldItem: FeatureItem, newItem: FeatureItem): Boolean {
            return oldItem.iconResId == newItem.iconResId
        }

        override fun areContentsTheSame(oldItem: FeatureItem, newItem: FeatureItem): Boolean {
            return oldItem == newItem
        }
    }
}
