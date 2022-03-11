package com.arabnetwork.nft.ui.fragments.main.wallet.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arabnetwork.nft.databinding.RowWalletSymbolListItemBinding
import com.arabnetwork.nft.models.NetworkModel
import com.hadilq.liveevent.LiveEvent
import com.hadilq.liveevent.LiveEventConfig


class WalletNetworkListRecAdapter :
    ListAdapter<NetworkModel, WalletNetworkListRecAdapter.ViewHolder>(WalletSymbolListDiffUtils) {

    companion object {
        private const val TAG = "WalletSymbolListRecAdapter"
    }

    object WalletSymbolListDiffUtils : DiffUtil.ItemCallback<NetworkModel>() {
        override fun areItemsTheSame(
            oldItem: NetworkModel,
            newItem: NetworkModel
        ): Boolean {
            return oldItem.networkId == newItem.networkId
        }

        override fun areContentsTheSame(
            oldItem: NetworkModel,
            newItem: NetworkModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val _onItemClicked: LiveEvent<NetworkModel> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)
    val onItemClicked: LiveData<NetworkModel> get() = _onItemClicked


    class ViewHolder(val binding: RowWalletSymbolListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(networkModel: NetworkModel) {
            binding.symbolModel = networkModel

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowWalletSymbolListItemBinding.inflate(layoutInflater, parent, false)
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

    fun setList(newData: List<NetworkModel>) {
        try {
            submitList(newData)
        } catch (e: Exception) {
            Log.e(TAG, "setList: ", e)
        }
    }
}

