package com.arabnetwork.nft.data.network

import com.arabnetwork.nft.models.nft.NftResultModel
import com.arabnetwork.nft.models.nft.NftTradeResultModel
import com.arabnetwork.nft.util.constants.ApiConstants.Companion.NFT_API_ENDPOINT_GET_NFT
import com.arabnetwork.nft.util.constants.ApiConstants.Companion.NFT_API_ENDPOINT_GET_NFT_TRADE
import com.arabnetwork.nft.util.constants.ApiConstants.Companion.NFT_API_PATH_PARAM_TOKEN_ADDRESS
import com.arabnetwork.nft.util.constants.ApiConstants.Companion.NFT_API_QUERY_PARAM_CHAIN
import com.arabnetwork.nft.util.constants.ApiConstants.Companion.NFT_API_QUERY_PARAM_FORMAT
import com.arabnetwork.nft.util.constants.ApiConstants.Companion.NFT_API_QUERY_PARAM_MARKETPLACE
import com.arabnetwork.nft.util.constants.ApiConstants.Companion.OWNER_OF_VALUE
import com.arabnetwork.nft.util.network.GenericResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NftService {

    @GET("$OWNER_OF_VALUE/$NFT_API_ENDPOINT_GET_NFT")
    suspend fun getNftListApi(
        @Query(NFT_API_QUERY_PARAM_CHAIN) chain: String,
        @Query(NFT_API_QUERY_PARAM_FORMAT) format: String,
    ): GenericResponseModel<NftResultModel>

    @GET("$NFT_API_ENDPOINT_GET_NFT/{$NFT_API_PATH_PARAM_TOKEN_ADDRESS}/$NFT_API_ENDPOINT_GET_NFT_TRADE")
    suspend fun getNftTradeApi(
        @Path(value = NFT_API_PATH_PARAM_TOKEN_ADDRESS, encoded = true) tokenAddress: String,
        @Query(NFT_API_QUERY_PARAM_CHAIN) chain: String,
        @Query(NFT_API_QUERY_PARAM_MARKETPLACE) marketplace: String,
    ): GenericResponseModel<NftTradeResultModel>


}