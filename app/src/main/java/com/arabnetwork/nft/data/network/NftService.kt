package com.arabnetwork.nft.data.network

import com.arabnetwork.nft.models.nft.NftResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NftService {

    @GET("0x31Bc1C722831DcdDCfc36323Ffc3d980a3C9AD51/nft")
    suspend fun getNftListApi(
        @Query("chain") chain: String,
        @Query("format") format: String,
    ): NftResponseModel
}