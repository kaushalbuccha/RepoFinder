package com.example.repofinder.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repofinder.api.RetrofitInstance
import com.example.repofinder.databinding.ActivitySavedRepositoriesBinding
import com.example.repofinder.db.RepositoryDatabase
import com.example.repofinder.model.Owner
import com.example.repofinder.model.Repository
import com.example.repofinder.repository.RepositoryRepository
import com.example.repofinder.ui.adapter.RepoAdapter
import com.example.repofinder.ui.adapter.SavedRepoAdapter
import com.example.repofinder.ui.viewmodel.SearchViewModel
import com.example.repofinder.ui.viewmodel.SearchViewModelFactory

class SavedRepositoriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySavedRepositoriesBinding
    private val viewModel: SearchViewModel by viewModels {
        val repoDao = RepositoryDatabase.getDatabase(application).repositoryDao()
        SearchViewModelFactory(RepositoryRepository(RetrofitInstance.apiService,repoDao))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedRepositoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeSavedRepositories()
    }
    private fun setupRecyclerView() {
        binding.rvSavedRepositories.layoutManager = LinearLayoutManager(this)
    }

    private fun observeSavedRepositories() {
        viewModel.getSavedRepositories().observe(this, Observer { repositoryEntities ->
            Log.d("OBSERVED","Observed repositoryEntities: $repositoryEntities")
            val repositories = repositoryEntities.map {
                val owner = Owner(it.ownerLogin, it.ownerAvatarUrl)
                Repository(it.id, it.name, it.description, it.html_url, owner, contributors_url = "")
            }

            repositories.forEach{ repositories->
                Log.d("REPO AFTER MAP","${repositories.name}  ${repositories.owner}")
            }
            val adapter = SavedRepoAdapter(repositories) { repo ->
                val intent = Intent(this, RepoDetailsActivity::class.java)
                intent.putExtra("REPO_DETAILS", repo)
                startActivity(intent)
            }
            binding.rvSavedRepositories.adapter = adapter
        })
    }
}