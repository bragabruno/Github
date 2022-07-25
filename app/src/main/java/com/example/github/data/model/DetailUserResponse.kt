package com.example.github.data.model

data class DetailUserResponse(
    val public_repos: Int,
    val avatar_url: String,
    val login: String,
    val email: String,
    val location: String,
    val created_at: String,
    val followers: String,
    val following: String,
    val bio: String
)
