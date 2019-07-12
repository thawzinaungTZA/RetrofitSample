package com.example.retrofitsample.api

import okhttp3.logging.HttpLoggingInterceptor
import com.example.retrofitsample.BuildConfig
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object HttpClientProvider {
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        builder.readTimeout(10, TimeUnit.SECONDS)
        builder.connectTimeout(5, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }
        return builder.build()
    }
}