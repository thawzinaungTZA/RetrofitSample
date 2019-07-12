package com.example.retrofitsample.api

import com.example.retrofitsample.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface PostApi {
    @GET("/posts")
    fun getAllPosts(): Call<List<Post>>
}