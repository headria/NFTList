package com.arabnetwork.nft.models.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkModel(

    var networkId: String? = "",

    var networkName: String? = "",

    var networkSymbol: String? = "",

    var networkIcon: Int? = 0,

    var networkNftCount: String? = ""

) : Parcelable



