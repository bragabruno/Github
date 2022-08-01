package com.example.github.api

import com.example.github.data.model.DetailUserResponse
import com.example.github.data.model.RepoResponse
import com.example.github.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/repos")
    @Headers("Authorization: token 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")
    fun getSearchRepos(
        @Path("username") username: String
    ): Call<RepoResponse>
}

// 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
