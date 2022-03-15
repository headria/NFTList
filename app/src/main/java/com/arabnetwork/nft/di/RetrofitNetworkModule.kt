package com.arabnetwork.nft.di

import com.arabnetwork.nft.data.network.NftService
import com.arabnetwork.nft.util.constants.ApiConstants.Companion.BASE_URL
import com.arabnetwork.nft.util.network.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitNetworkModule {

    @CloudRetrofit
    @Provides
    @Singleton
    fun provideOkhttp(tokenInterceptor: TokenInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okhttpBuilder =
            OkHttpClient().newBuilder().readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(tokenInterceptor)
        return okhttpBuilder.build()
    }

    // create function for satisfy gson converter factory dependency
    @CloudRetrofit
    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    // create a new function named to provide the retrofit instance
    @CloudRetrofit
    @Singleton
    @Provides
    fun provideRetrofitInstance(
        @CloudRetrofit okHttpClient: OkHttpClient,
        @CloudRetrofit gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideNftService(
        @CloudRetrofit
        retrofit: Retrofit
    ): NftService {
        return retrofit.create(NftService::class.java)
    }


}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CloudRetrofit

