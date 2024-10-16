package com.example.repofinder.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.repofinder.R
import com.example.repofinder.model.Repository

class RepoAdapter(private val onClick: (Repository) -> Unit) :
    ListAdapter<Repository, RepoAdapter.RepoViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position)
        holder.bind(repo, onClick)
    }

    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(repo: Repository, onClick: (Repository) -> Unit) {
            itemView.findViewById<TextView>(R.id.repo_name).text = repo.name
            itemView.setOnClickListener { onClick(repo) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem == newItem
        }
    }
}