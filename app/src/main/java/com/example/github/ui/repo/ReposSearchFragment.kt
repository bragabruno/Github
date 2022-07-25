package com.example.github.ui.repo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.github.R
import com.example.github.databinding.FragmentReposSearchBinding
import com.example.github.ui.detail.DetailUserActivity

class ReposSearchFragment : Fragment(R.layout.fragment_repos_search) {

    private lateinit var _binding: FragmentReposSearchBinding
    private val binding get() = _binding
    private lateinit var repoViewModel: RepoViewModel
    private lateinit var adapter: RepoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentReposSearchBinding.bind(view)

        adapter = RepoAdapter()
        adapter.notifyDataSetChanged()

//        adapter.setOnItemClickCallback(object : RepoAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: Repo) {
//                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
//                startActivity(browserIntent)
//            }
//        })
        repoViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(RepoViewModel::class.java)

        binding.apply {
            rvRepos.layoutManager = LinearLayoutManager(this@ReposSearchFragment.context)
            rvRepos.setHasFixedSize(true)
            rvRepos.adapter = adapter

//            if (userActivity.isNotEmpty()) {
            userRepos()
//            }

//            if (etQuery.text?.isNotEmpty() == true) {
//                etQuery.doAfterTextChanged { searchRepo() }
//            }
        }
        repoViewModel.getUserRepos().observe(viewLifecycleOwner, {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        })
    }

    fun userRepos() {
        val activity = getActivity() as DetailUserActivity
        val userActivity: String = activity.getCurrentUserName()
        binding.apply {
            val username = userActivity
            if (username.isEmpty()) return
            showLoading(true)
            repoViewModel.setUserRepos(username)
        }
    }

//    fun searchRepo() {
//        binding.apply {
//            val username = etQuery.text.toString()
//            if (username.isEmpty()) return
//            showLoading(true)
//            repoViewModel.setUserRepos(username)
//        }
//    }
// private fun searchRepo() {
//        binding.apply {
//            val repoSearch = etQuery.text.toString()
//            if (repoSearch.isEmpty()) {
//                return showLoading(true)
//            }
//        }
//    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}
