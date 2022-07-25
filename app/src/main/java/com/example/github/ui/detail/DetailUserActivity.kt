package com.example.github.ui.detail

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.github.R
import com.example.github.api.RetrofitClient
import com.example.github.data.model.DetailUserResponse
import com.example.github.databinding.ActivityDetailUserBinding
import com.example.github.ui.repo.ReposSearchFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel
    private val reposSearchFragment = ReposSearchFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reposSearchFragment
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailUserViewModel::class.java)

        if (username != null)
            viewModel.setUserDetail(username)

        viewModel.getUserDetail().observe(this, {
            if (it != null) {
                binding.apply {
                    tvUsername.text = it.login
                    tvBio.text = it.bio
                    tvEmail.text = it.email
                    tvFollowers.text = "${it.followers} Followers"
                    tvFollowing.text = "${it.following} Following"
                    tvJoinDate.text = it.created_at
                    tvLocation.text = it.location
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(ivUser)
                }
            }
        })
    }

    fun getCurrentUserName(): String{

    val textView: TextView = findViewById(R.id.tv_username) as TextView
        return textView.text.toString()
    }

}
