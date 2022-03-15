package com.arabnetwork.nft.util.network

import android.content.Context
import com.arabnetwork.nft.util.constants.ApiConstants.Companion.X_API_KEY_KEY
import com.arabnetwork.nft.util.constants.ApiConstants.Companion.X_API_KEY_VALUE
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TokenInterceptor @Inject constructor(
    @ApplicationContext val applicationContext: Context
) : Interceptor {

    private var apiKeyValue: String = X_API_KEY_VALUE


    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val requestBuilder = original.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader(X_API_KEY_KEY, apiKeyValue)
            .url(originalHttpUrl)
        val requestHeader = requestBuilder.build()
        return chain.proceed(requestHeader)
    }
}