package com.example.github.ui.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.github.api.RetrofitClient
import com.example.github.data.model.Repo
import com.example.github.data.model.RepoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoViewModel : ViewModel() {

    val listRepos = MutableLiveData<ArrayList<Repo>>()

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
