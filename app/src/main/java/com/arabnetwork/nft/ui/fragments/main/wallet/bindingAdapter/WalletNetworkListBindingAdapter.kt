package com.arabnetwork.nft.ui.fragments.main.wallet.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

class WalletNetworkListBindingAdapter {

    companion object {

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String?) {
            imageView.load(imageUrl)
        }
    }
}