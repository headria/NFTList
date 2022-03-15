package com.arabnetwork.nft.models.nft

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NftResultModel(

    @SerializedName("token_address")
    var tokenAddress: String? = "",
    @SerializedName("token_id")
    var tokenId: String? = "",
    @SerializedName("contract_type")
    var contractType: String? = "",
    @SerializedName("owner_of")
    var ownerOf: String? = "",
    @SerializedName("block_number")
    var blockNumber: String? = "",
    @SerializedName("block_number_minted")
    var blockNumberMinted: String? = "",
    @SerializedName("token_uri")
    var tokenUri: String? = "",
    @SerializedName("metadata")
    var metadata: String? = "",
    @SerializedName("synced_at")
    var syncedAt: String? = "",
    @SerializedName("amount")
    var amount: String? = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("symbol")
    var symbol: String? = ""

) : Parcelable