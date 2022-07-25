package com.example.github.data.model

import com.google.gson.annotations.SerializedName

data class RepoResponse(
    @SerializedName("repoItems")
    val repoItems: ArrayList<Repo>

)
