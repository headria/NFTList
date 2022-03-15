package com.arabnetwork.nft.data.repository

import com.arabnetwork.nft.data.network.NftService
import com.arabnetwork.nft.util.network.NetworkUtil.safeApiCall
import javax.inject.Inject

class NftRepository @Inject constructor(
    private var nftService: NftService
) {

    suspend fun getNftList() = safeApiCall {
        nftService.getNftListApi(chain = "eth", format = "decimal")
    }
}