package com.example.repofinder.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repofinder.databinding.ActivityRepoDetailsBinding
import com.example.repofinder.model.Contributor
import com.example.repofinder.model.Repository
import kotlinx.coroutines.launch
import com.bumptech.glide.Glide
import com.example.repofinder.api.RetrofitInstance
import com.example.repofinder.ui.adapter.ContributorAdapter

class RepoDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepoDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = intent.getSerializableExtra("REPO_DETAILS") as? Repository
        if (repository == null) {
            Toast.makeText(this, "Repository data not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        binding.tvRepoName.text = repository.name
        binding.tvRepoDescription.text = repository.description
        binding.tvRepoLink.text = repository.html_url


        Glide.with(this)
            .load(repository.owner.avatar_url)
            .into(binding.ivOwnerAvatar)


        binding.tvRepoLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repository.html_url))
            startActivity(intent)
        }

        lifecycleScope.launch {
            fetchContributors(repository.contributors_url)
        }
    }
    private suspend fun fetchContributors(contributorsUrl: String) {
        try {
            val contributors = RetrofitInstance.apiService.getContributors(contributorsUrl)
            displayContributors(contributors)
        } catch (e: Exception) {
            //Log.e("RepoDetailsActivity", "Error fetching contributors: ${e.message}", e)
            Toast.makeText(this, "Error fetching contributors: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun displayContributors(contributors: List<Contributor>) {
        if (contributors.isNotEmpty()) {

            val adapter = ContributorAdapter(contributors)
            binding.rvContributors.layoutManager = LinearLayoutManager(this)
            binding.rvContributors.adapter = adapter
        } else {
            Toast.makeText(this, "No contributors found", Toast.LENGTH_SHORT).show()
        }
    }
//    private fun setupContributorsRecyclerView(contributors: List<Contributor>) {
//        val adapter = ContributorAdapter(contributors)
//        binding.rvContributors.layoutManager = LinearLayoutManager(this)
//        binding.rvContributors.adapter = adapter
//    }
}