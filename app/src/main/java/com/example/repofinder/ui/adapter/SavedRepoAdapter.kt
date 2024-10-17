package com.example.repofinder.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.repofinder.R
import com.example.repofinder.model.Repository

class SavedRepoAdapter(
    private val repositories: List<Repository>,
    private val onClick: (Repository) -> Unit
) : RecyclerView.Adapter<SavedRepoAdapter.RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = repositories[position]
        holder.bind(repo, onClick)
    }

    override fun getItemCount(): Int = repositories.size

    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(repo: Repository, onClick: (Repository) -> Unit) {
            itemView.findViewById<TextView>(R.id.repo_name).text = repo.name
            itemView.setOnClickListener { onClick(repo) }
        }
    }
}