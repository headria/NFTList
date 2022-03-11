package com.arabnetwork.nft.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ConfirmModel(

    var confirmFrom: String? = "",
    var confirmTo: String? = "",
    var confirmNetworkFee: String? = "",

    ) : Parcelable