package com.arabnetwork.nft.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class TransactionFeeModel(

    var transFeeTotalFee    : String? = "",
    var transFeeCurrentGWEI : String? = "",
    var transFeeCoinSymbol  : String? = "",
    var transFeeSlowGWEI    : String? = "",
    var transFeeSlowMin     : String? = "",
    var transFeeMediumGWEI  : String? = "",
    var transFeeMediumMin   : String? = "",
    var transFeeFastGWEI    : String? = "",
    var transFeeFastMin     : String? = "",
) : Parcelable