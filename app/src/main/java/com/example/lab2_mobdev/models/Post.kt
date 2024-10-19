package com.example.lab2_mobdev.models

data class Post(
    val username: String,
    val caption: String,
    val imageResource: Int,
    var likes: Int
)
