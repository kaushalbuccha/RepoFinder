package com.example.repofinder.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.repofinder.R
import com.example.repofinder.model.Contributor

class ContributorAdapter(private val contributors: List<Contributor>) :
    RecyclerView.Adapter<ContributorAdapter.ContributorViewHolder>() {

    class ContributorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar: ImageView = view.findViewById(R.id.iv_contributor_avatar)
        val login: TextView = view.findViewById(R.id.tv_contributor_login)
        val contributions: TextView = view.findViewById(R.id.tv_contributions)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contributor, parent, false)
        return ContributorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        val contributor = contributors[position]

        holder.login.text = contributor.login
        holder.contributions.text = "Contributions: ${contributor.contributions}"

        Glide.with(holder.avatar.context)
            .load(contributor.avatar_url)
            .into(holder.avatar)

        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(contributor.avatar_url))
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = contributors.size
}
