package com.arabnetwork.nft.ui.fragments.main.wallet.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arabnetwork.nft.databinding.RowWalletNetworkListItemBinding
import com.arabnetwork.nft.models.network.NetworkModel
import com.hadilq.liveevent.LiveEvent
import com.hadilq.liveevent.LiveEventConfig
import java.util.ArrayList


class WalletNetworkListRecAdapter :
    ListAdapter<NetworkModel, WalletNetworkListRecAdapter.ViewHolder>(WalletSymbolListDiffUtils) {

    companion object {
        private const val TAG = "WalletNetworkListRecAdapter"
    }

    private var searchList : ArrayList<NetworkModel> = ArrayList()

    private val _isSearchItemAvailable: LiveEvent<Boolean> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)
    val isSearchItemAvailable: LiveData<Boolean> get() = _isSearchItemAvailable

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


    class ViewHolder(val binding: RowWalletNetworkListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(networkModel: NetworkModel) {
            binding.networkModel = networkModel

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowWalletNetworkListItemBinding.inflate(layoutInflater, parent, false)
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

    fun setList(newData: List<NetworkModel>,isSearch: Boolean = false) {
        try {
            if (!isSearch)
                searchList = ArrayList(newData)
            submitList(newData)
        } catch (e: Exception) {
            Log.e(TAG, "setList: ", e)
        }
    }

    fun searchOnNetworkList(searchChar: String) {
        try {
            val list = searchList.clone() as ArrayList<NetworkModel>
            val filtered = list.filter {
                it.networkName!!.contains(searchChar, true)
            }.sortedByDescending {
                it.networkName!!.startsWith(searchChar, true)
            }
            if (filtered.isNotEmpty()) {
                _isSearchItemAvailable.value = true
                setList((filtered), true)
            } else
                _isSearchItemAvailable.value = false
        } catch (e: java.lang.Exception) {
            Log.e(TAG, "searchPossibleChar: $e")
        }
    }
}

