package com.example.movies_mobile2you.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies_mobile2you.R
import com.example.movies_mobile2you.adapter.LoadState
import com.example.movies_mobile2you.databinding.ItemLoadStateBinding
import com.example.movies_mobile2you.extension.visible
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class LoadStateAdapter @Inject constructor() : RecyclerView.Adapter<LoadStateAdapter.LoadStateViewHolder>() {

    var loadState: LoadState = LoadState.Done
        set(value) {
            when (field) {
                value -> {
                    notifyItemChanged(0)
                }
                is LoadState.Loading -> {
                    itemsCount = 0
                    notifyItemRemoved(0)
                }
                is LoadState.Done -> {
                    itemsCount = 1
                    notifyItemInserted(1)
                }
            }
            field = value
        }

    private var itemsCount: Int = 0

    inner class LoadStateViewHolder(private val binding: ItemLoadStateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.progress.visible(
                visible = LoadState.Loading == loadState
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LoadStateViewHolder(
        ItemLoadStateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemViewType(position: Int): Int = R.layout.item_load_state
    override fun getItemCount(): Int = itemsCount
    override fun onBindViewHolder(holder: LoadStateViewHolder, position: Int) =
        holder.bind(loadState)
}