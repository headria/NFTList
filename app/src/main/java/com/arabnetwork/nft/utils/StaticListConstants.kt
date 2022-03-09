package com.arabnetwork.nft.utils

import com.arabnetwork.nft.models.NftModel
import com.arabnetwork.nft.models.SymbolModel

class StaticListConstants {

    companion object {

        val wallet_symbol_list: List<SymbolModel> = listOf(
            SymbolModel(
                coinId = "1",
                coinName = "Bitcoin",
                coinSymbol = "BTC",
                coinIcon = "https://s2.coinmarketcap.com/static/img/coins/64x64/1.png",
                coinNftCount = "16 NFT"
            ),
            SymbolModel(
                coinId = "2",
                coinName = "Ethereum",
                coinSymbol = "ETH",
                coinIcon = "https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png",
                coinNftCount = "16 NFT"
            ),
            SymbolModel(
                coinId = "3",
                coinName = "Tether",
                coinSymbol = "USDT",
                coinIcon = "https://s2.coinmarketcap.com/static/img/coins/64x64/825.png",
                coinNftCount = "16 NFT"
            ),
            SymbolModel(
                coinId = "4",
                coinName = "Binance Coin",
                coinSymbol = "BNB",
                coinIcon = "https://s2.coinmarketcap.com/static/img/coins/64x64/1839.png",
                coinNftCount = "16 NFT"
            ),
            SymbolModel(
                coinId = "5",
                coinName = "USD Coin",
                coinSymbol = "USDC",
                coinIcon = "https://s2.coinmarketcap.com/static/img/coins/64x64/3408.png",
                coinNftCount = "16 NFT"
            ),
            SymbolModel(
                coinId = "6",
                coinName = "XRP",
                coinSymbol = "XRP",
                coinIcon = "https://s2.coinmarketcap.com/static/img/coins/64x64/52.png",
                coinNftCount = "16 NFT"
            ),
            SymbolModel(
                coinId = "7",
                coinName = "Terra",
                coinSymbol = "LUNA",
                coinIcon = "https://s2.coinmarketcap.com/static/img/coins/64x64/4172.png",
                coinNftCount = "16 NFT"
            ),
            SymbolModel(
                coinId = "8",
                coinName = "Cardano",
                coinSymbol = "Ada",
                coinIcon = "https://s2.coinmarketcap.com/static/img/coins/64x64/2010.png",
                coinNftCount = "16 NFT"
            ),
        )

        val trade_nft_list: List<NftModel> = listOf(
            NftModel(
                nftId = "1",
                nftImage = "https://image-cdn.hypb.st/https%3A%2F%2Fhypebeast.com%2Fimage%2F2021%2F10%2Fbored-ape-yacht-club-nft-3-4-million-record-sothebys-metaverse-0.jpg?w=960&cbr=1&q=90&fit=max",
                nftName = "Monkey Happy",
                nftCoin = "Bitcoin",
                nftCoinPrice = "0.0025",
                nftAddress = "0845rftjhdytrnpsjfyrtbcge3218lprn",
                nftCount = "0.2"
            ),
            NftModel(
                nftId = "2",
                nftImage = "https://i.pinimg.com/736x/4a/d2/7f/4ad27f8477b8e3f7548fe1f806c374c4.jpg",
                nftName = "Space Crystal",
                nftCoin = "Ethereum",
                nftCoinPrice = "0.00015",
                nftAddress = "0845rftjhdytrnpsjfyrtbcge3218lprn",
                nftCount = "0.1"
            ),
            NftModel(
                nftId = "3",
                nftImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT-ShMHZtKdq5VkDT7XaUigTcP0Uw1JWP2llg&usqp=CAU",
                nftName = "Human on Color",
                nftCoin = "Tether",
                nftCoinPrice = "0.00035",
                nftAddress = "0845rftjhdytrnpsjfyrtbcge3218lprn",
                nftCount = "0.2"
            ),
            NftModel(
                nftId = "4",
                nftImage = "https://pixelguysnft.com/images/874.png",
                nftName = "Pixel",
                nftCoin = "Binance Coin",
                nftCoinPrice = "0.00045",
                nftAddress = "0845rftjhdytrnpsjfyrtbcge3218lprn",
                nftCount = "0.1"
            ),
            NftModel(
                nftId = "5",
                nftImage = "https://image-cdn.hypb.st/https%3A%2F%2Fhypebeast.com%2Fimage%2F2021%2F10%2Fbored-ape-yacht-club-nft-3-4-million-record-sothebys-metaverse-0.jpg?w=960&cbr=1&q=90&fit=max",
                nftName = "Monkey Happy",
                nftCoin = "USD Coin",
                nftCoinPrice = "0.0000055",
                nftAddress = "0845rftjhdytrnpsjfyrtbcge3218lprn",
                nftCount = "0.2"
            ),
            NftModel(
                nftId = "6",
                nftImage = "https://image-cdn.hypb.st/https%3A%2F%2Fhypebeast.com%2Fimage%2F2021%2F10%2Fbored-ape-yacht-club-nft-3-4-million-record-sothebys-metaverse-0.jpg?w=960&cbr=1&q=90&fit=max",
                nftName = "Space Crystal",
                nftCoin = "XRP",
                nftCoinPrice = "0.065",
                nftAddress = "0845rftjhdytrnpsjfyrtbcge3218lprn",
                nftCount = "0.1"
            ),
        )

    }
}