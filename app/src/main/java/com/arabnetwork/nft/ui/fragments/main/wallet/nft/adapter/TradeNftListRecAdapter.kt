package com.arabnetwork.nft.ui.fragments.main.wallet.nft.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arabnetwork.nft.databinding.RowTradeNftListItemBinding
import com.arabnetwork.nft.databinding.RowWalletSymbolListItemBinding
import com.arabnetwork.nft.models.NftModel
import com.arabnetwork.nft.models.SymbolModel
import com.hadilq.liveevent.LiveEvent
import com.hadilq.liveevent.LiveEventConfig


class TradeNftListRecAdapter :
    ListAdapter<NftModel, TradeNftListRecAdapter.ViewHolder>(WalletSymbolListDiffUtils) {

    companion object {
        private const val TAG = "TradeNftListRecAdapter"
    }

    object WalletSymbolListDiffUtils : DiffUtil.ItemCallback<NftModel>() {
        override fun areItemsTheSame(
            oldItem: NftModel,
            newItem: NftModel
        ): Boolean {
            return oldItem.nftId == newItem.nftId
        }

        override fun areContentsTheSame(
            oldItem: NftModel,
            newItem: NftModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val _onItemClicked: LiveEvent<NftModel> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)
    val onItemClicked: LiveData<NftModel> get() = _onItemClicked


    class ViewHolder(val binding: RowTradeNftListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(nftModel: NftModel) {
            binding.nftModel = nftModel

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowTradeNftListItemBinding.inflate(layoutInflater, parent, false)
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

    fun setList(newData: List<NftModel>) {
        try {
            submitList(newData)
        } catch (e: Exception) {
            Log.e(TAG, "setList: ", e)
        }
    }
}

