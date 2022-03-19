package com.arabnetwork.nft.ui.fragments.main.wallet.nft.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.arabnetwork.nft.util.NftUtil

class NftBindingAdapter {

    companion object {
        private const val TAG = "NftBindingAdapter"

        @BindingAdapter("loadImageFromJson")
        @JvmStatic
        fun loadImageFromJson(imageView: ImageView, jsonObject: String) {
            NftUtil.nftImageLoader(imageView,jsonObject)
        }

    }
}