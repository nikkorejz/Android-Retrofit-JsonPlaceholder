package com.example.networkdemo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetHandler {

    companion object {
        val retrofit by lazy {
            with(Retrofit.Builder()) {
                baseUrl("https://jsonplaceholder.typicode.com")
                addConverterFactory(GsonConverterFactory.create())
                build()
            }
        }

        val postsApi by lazy {
            retrofit.create(PostsApi::class.java)
        }
    }

}