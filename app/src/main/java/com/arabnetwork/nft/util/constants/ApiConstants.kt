package com.arabnetwork.nft.util.constants

class ApiConstants {

    companion object {
        const val BASE_URL = "https://deep-index.moralis.io/api/v2/"

        const val X_API_KEY_KEY = "X-API-Key"
        const val X_API_KEY_VALUE =
            "stTppRkiwKy0sPwYc2jSBjM5vuvNXq83dhmh7vzT08Q9kvxC8mciuWldsunDIq8F"

        const val OWNER_OF_VALUE = "0x31Bc1C722831DcdDCfc36323Ffc3d980a3C9AD51"

        // error messages
        const val ERROR_TIME_OUT = "Response Timeout"
        const val ERROR_NO_SERVER = "Unable to resolve host"
        const val ERROR_NO_NETWORK = "No Internet Connection."
        const val UNKNOWN_ERROR = "Unknown Error."
        const val NFT_LIST_IS_EMPTY = "Nft List is Empty"
        const val NFT_LIST_IS_NOT_EMPTY = ""
        const val NFT_TRADE_IS_NOT_AVAILABLE = "there is no nft trade for this token"

        // nft path param
        const val NFT_API_PATH_PARAM_TOKEN_ADDRESS = "tokenAddress"

        // nft query param
        const val NFT_API_QUERY_PARAM_CHAIN = "chain"
        const val NFT_API_QUERY_PARAM_FORMAT = "format"
        const val NFT_API_QUERY_PARAM_MARKETPLACE = "marketplace"

        // nft endpoints
        const val NFT_API_ENDPOINT_GET_NFT = "nft"
        const val NFT_API_ENDPOINT_GET_NFT_TRADE = "trades"
    }
}