package com.example.networkdemo

data class Post(
    val userId: Int,
    val id: Int = -1,
    val title: String,
    val body: String,
)