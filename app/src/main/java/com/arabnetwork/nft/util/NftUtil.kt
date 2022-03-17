package com.arabnetwork.nft.util

import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.loadAny
import com.arabnetwork.nft.R
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.util.regex.Matcher
import java.util.regex.Pattern


object NftUtil {

    fun nftImageLoader(imageView: ImageView, jsonObject: String) {
        val gson = Gson()
        val type = object : TypeToken<JsonObject>() {}.type
        val errorResponse: JsonObject? =
            gson.fromJson(jsonObject, type)

        val imageUrl = errorResponse?.asJsonObject?.get("image").toString()
        val result = removeDoubleQuotationFromString(quotationStr = imageUrl)
        if (!isContainVideo(result))
            imageView.loadAny(result, imageLoader = ImageLoader.Builder(imageView.context)
                .componentRegistry {
                    add(SvgDecoder(imageView.context))
                }
                .build())
        else
            imageView.load(R.drawable.ic_play)
    }

    private fun removeDoubleQuotationFromString(quotationStr: String): String {
        return quotationStr.substring(1, quotationStr.length - 1)
    }

    private fun isContainVideo(str: String): Boolean {
        val patternString = "mp4"
        val pattern: Pattern = Pattern.compile(patternString)
        val matcher: Matcher = pattern.matcher(str)
        return matcher.find()
    }

}