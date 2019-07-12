package com.example.retrofitsample.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestAdapter {
    const val BASE_URL = "https://jsonplaceholder.typicode.com"
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(HttpClientProvider.provideOkHttpClient())
                .build()
        }
        return retrofit!!
    }
}