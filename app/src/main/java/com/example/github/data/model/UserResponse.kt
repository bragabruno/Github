package com.example.github.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("items")
    val items: ArrayList<User>

)
