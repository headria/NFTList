package com.arabnetwork.nft.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NftModel(

    var nftId: String? = "",

    var nftImage: String? = "",

    var nftName: String? = "",

    var nftCoin: String? = "",

    var nftCoinPrice: String? = "",

    var nftAddress: String? = "",

    var nftDescription: String? = "",

    var nftCount: String? = "",
) : Parcelable



