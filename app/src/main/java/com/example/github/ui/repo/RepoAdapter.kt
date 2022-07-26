package com.example.github.ui.repo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.github.data.model.Repo
import com.example.github.data.model.RepoResponse
import com.example.github.databinding.ItemReposBinding

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    private var listRepoData = ArrayList<Repo>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(repos: List<Repo>) {
        listRepoData.clear()
        listRepoData.addAll(repos)
        notifyDataSetChanged()
    }

    inner class RepoViewHolder(val binding: ItemReposBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: Repo) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(repo)
            }
            binding.apply {
                tvRepoName.text = repo.name
                tvForks.text = "${repo.forks_count} Forks"
                tvStarts.text = "${repo.stargazers_count} Stars"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = ItemReposBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder((view))
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(listRepoData[position])
    }

    override fun getItemCount(): Int = listRepoData.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Repo)
    }
}
