package com.arabnetwork.nft.util.network

import com.google.gson.annotations.SerializedName

data class GenericResponseModel<T>(
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
    var results: ArrayList<T>

)