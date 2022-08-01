package com.example.github.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.github.data.model.DetailUserViewModel
import com.example.github.databinding.ActivityDetailUserBinding
import com.example.github.ui.repo.RepoAdapter

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var activituDetailUserBinding: ActivityDetailUserBinding
    private lateinit var detailUserViewModel: DetailUserViewModel
    private lateinit var repoAdapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activituDetailUserBinding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(activituDetailUserBinding.root)

        repoAdapter = RepoAdapter()
        repoAdapter.notifyDataSetChanged()

        val username = intent.getStringExtra(EXTRA_USERNAME)!!

//        username.let { updateuserRepos(it) }

        detailUserViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailUserViewModel::class.java)

        detailUserViewModel.setUserDetail(username)

        detailUserViewModel.getUserDetail().observe(this, {
            if (it != null) {
                activituDetailUserBinding.apply {
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
        updateRepoList(username)
    }

    fun updateRepoList(username: String) {
//        repoAdapter.setOnItemClickCallback(object : RepoAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: Repo) {
//                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
//                startActivity(browserIntent)
//            }
//        })

        activituDetailUserBinding.apply {
            rvRepos.layoutManager = LinearLayoutManager(this@DetailUserActivity)
            rvRepos.setHasFixedSize(true)
            rvRepos.adapter = repoAdapter

            btnSearch.setOnClickListener {
                searchUserRepos(username)
            }
            searchUserRepos(username)
//            etQuery.doAfterTextChanged {
//                val list: List<Repo> = listRepos.filter { repo -> repo.name.contains(activituDetailUserBinding.etQuery.text.toString()) }
//                if (it != null) {
//                    repoAdapter.setList(list)
//                }
//            }
        }
        detailUserViewModel.getUserRepos().observe(this, {
            repoAdapter.setList(it)
            showLoading(false)
        })
    }

    fun searchUserRepos(username: String) {
        activituDetailUserBinding.apply {
            if (username.isEmpty()) return
            showLoading(true)
            detailUserViewModel.setUserRepos(username)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            activituDetailUserBinding.progressBar.visibility = View.VISIBLE
        } else {
            activituDetailUserBinding.progressBar.visibility = View.GONE
        }
    }
}
