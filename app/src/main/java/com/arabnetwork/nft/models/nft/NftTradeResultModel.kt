package com.arabnetwork.nft.models.nft

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NftTradeResultModel(

    @SerializedName("transaction_hash")
    var transactionHash: String? = "",
    @SerializedName("transaction_index")
    var transactionIndex: String? = "",
    @SerializedName("token_ids")
    var tokenIds: ArrayList<String>?,
    @SerializedName("seller_address")
    var sellerAddress: String? = "",
    @SerializedName("token_address")
    var tokenAddress: String? = "",
    @SerializedName("marketplace_address")
    var marketplaceAddress: String? = "",
    @SerializedName("price")
    var price: String? = "",
    @SerializedName("price_token_address")
    var priceTokenAddress: String? = "",
    @SerializedName("block_timestamp")
    var blockTimestamp: String? = "",
    @SerializedName("block_number")
    var blockNumber: String? = "",
    @SerializedName("block_hash")
    var blockHash: String? = "",


    ) : Parcelable