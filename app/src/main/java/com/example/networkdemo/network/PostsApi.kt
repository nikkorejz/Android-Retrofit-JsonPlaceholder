package com.example.networkdemo.network

import com.example.networkdemo.Comment
import com.example.networkdemo.Post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostsApi {

    @GET("/posts")
    suspend fun getAll(): Collection<Post>

    @GET("/posts")
    fun getAllSync(): Call<Collection<Post>>

    @GET("/posts/{id}/comments")
    suspend fun getComments(@Path("id") id: String): Collection<Comment>

    @POST("/posts")
    suspend fun create(@Body post: Post): Post

}