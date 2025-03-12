package com.example.lab1.ui.profile

data class Post(
    val text: String,
    val imageUrl: String?,
    var likes: Int,
    var comments: Int
)
