package com.arabnetwork.nft.ui.fragments.main.wallet.nft.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arabnetwork.nft.databinding.RowNftListItemBinding
import com.arabnetwork.nft.models.nft.NftResultModel
import com.hadilq.liveevent.LiveEvent
import com.hadilq.liveevent.LiveEventConfig


class NftListRecAdapter :
    ListAdapter<NftResultModel, NftListRecAdapter.ViewHolder>(NftListDiffUtils) {

    companion object {
        private const val TAG = "NftListRecAdapter"
    }

    object NftListDiffUtils : DiffUtil.ItemCallback<NftResultModel>() {
        override fun areItemsTheSame(
            oldItem: NftResultModel,
            newItem: NftResultModel
        ): Boolean {
            return oldItem.tokenId == newItem.tokenId
        }

        override fun areContentsTheSame(
            oldItem: NftResultModel,
            newItem: NftResultModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val _onItemClicked: LiveEvent<NftResultModel> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)
    val onItemClicked: LiveData<NftResultModel> get() = _onItemClicked


    class ViewHolder(val binding: RowNftListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(nftResultModel: NftResultModel) {
            binding.nftResultModel = nftResultModel
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowNftListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (currentList.isNullOrEmpty()) {
            return
        }
        try {
            currentList[position]?.let { item ->
                holder.itemView.setOnClickListener {
                    _onItemClicked.value = item
                }
                holder.bind(item)
            }
        } catch (e: Exception) {
            Log.e(TAG, "onBindViewHolder: ", e)
        }
    }

    fun setList(newData: List<NftResultModel>) {
        try {
            submitList(newData)
        } catch (e: Exception) {
            Log.e(TAG, "setList: ", e)
        }
    }
}

