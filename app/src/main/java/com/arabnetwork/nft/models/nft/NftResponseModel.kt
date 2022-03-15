package com.arabnetwork.nft.models.nft

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NftResponseModel(

    @SerializedName("total")
    var total: Int? = 0,
    @SerializedName("page")
    var page: Int? = 0,
    @SerializedName("page_size")
    var pageSize: Int? = 0,
    @SerializedName("cursor")
    var cursor: String? = "",
    @SerializedName("status")
    var status: String? = "",
    @SerializedName("result")
    var nftResultModelList: ArrayList<NftResultModel>

) : Parcelable