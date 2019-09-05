package com.vidyacharan.urbandictionary.data.remote

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.vidyacharan.urbandictionary.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

object Networking {
    internal const val HEADER_API_HOST = "x-rapidapi-host"
    internal const val HEADER_API_KEY = "x-rapidapi-key"

    internal lateinit var apiKey: String
    internal lateinit var apiHost: String

    fun create(apiHost: String, apiKey: String, baseUrl: String, cacheDir: File, cacheSize: Long): NetworkService {
        this.apiHost = apiHost
        this.apiKey = apiKey

        val okHttpClient = OkHttpClient.Builder()
            .cache(Cache(cacheDir, cacheSize))
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE
                    })
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(NetworkService::class.java)
    }
}