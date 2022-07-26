package com.example.github.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.github.api.RetrofitClient
import com.example.github.data.model.DetailUserResponse
import com.example.github.data.model.Repo
import com.example.github.data.model.RepoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {
    val usersDetail = MutableLiveData<DetailUserResponse>()

    val listRepos = MutableLiveData<ArrayList<Repo>>()

    fun setUserDetail(username: String) {
        RetrofitClient.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<DetailUserResponse> {
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if (response.isSuccessful) {
                        usersDetail.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }

    fun getUserDetail(): LiveData<DetailUserResponse> {
        return usersDetail
    }

    fun setUserRepos(username: String) {
        RetrofitClient.apiInstance
            .getSearchRepos(username)
            .enqueue(object : Callback<RepoResponse> {
                override fun onResponse(
                    call: Call<RepoResponse>,
                    response: Response<RepoResponse>
                ) {
                    if (response.isSuccessful) {
                        listRepos.postValue(response.body()?.repoItems)
                    }
                }

                override fun onFailure(call: Call<RepoResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }

    fun getUserRepos(): LiveData<ArrayList<Repo>> {
        return listRepos
    }
}
