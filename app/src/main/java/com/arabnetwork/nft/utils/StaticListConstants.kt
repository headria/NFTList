package com.arabnetwork.nft.utils

import com.arabnetwork.nft.models.SymbolModel

class StaticListConstants {

    companion object {

        val wallet_symbol_list: List<SymbolModel> = listOf(
            SymbolModel(
                coinId = "1",
                name = "Bitcoin",
                symbol = "BTC",
                icon = "https://s2.coinmarketcap.com/static/img/coins/64x64/1.png",
                nftCount = "16 NFT"
            ),
            SymbolModel(
                coinId = "2",
                name = "Ethereum",
                symbol = "ETH",
                icon = "https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png",
                nftCount = "16 NFT"
            ),
            SymbolModel(
                coinId = "3",
                name = "Tether",
                symbol = "USDT",
                icon = "https://s2.coinmarketcap.com/static/img/coins/64x64/825.png",
                nftCount = "16 NFT"
            ),
            SymbolModel(
                coinId = "4",
                name = "Binance Coin",
                symbol = "BNB",
                icon = "https://s2.coinmarketcap.com/static/img/coins/64x64/1839.png",
                nftCount = "16 NFT"
            ),
            SymbolModel(
                coinId = "5",
                name = "USD Coin",
                symbol = "USDC",
                icon = "https://s2.coinmarketcap.com/static/img/coins/64x64/3408.png",
                nftCount = "16 NFT"
            ),
            SymbolModel(
                coinId = "6",
                name = "XRP",
                symbol = "XRP",
                icon = "https://s2.coinmarketcap.com/static/img/coins/64x64/52.png",
                nftCount = "16 NFT"
            ),
            SymbolModel(
                coinId = "7",
                name = "Terra",
                symbol = "LUNA",
                icon = "https://s2.coinmarketcap.com/static/img/coins/64x64/4172.png",
                nftCount = "16 NFT"
            ),
            SymbolModel(
                coinId = "8",
                name = "Cardano",
                symbol = "Ada",
                icon = "https://s2.coinmarketcap.com/static/img/coins/64x64/2010.png",
                nftCount = "16 NFT"
            ),
        )
    }
}