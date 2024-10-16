package com.example.repofinder.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.repofinder.R
import com.example.repofinder.api.RetrofitInstance
import com.example.repofinder.db.RepositoryDatabase
import com.example.repofinder.repository.RepositoryRepository
import com.example.repofinder.ui.adapter.RepoAdapter
import com.example.repofinder.ui.viewmodel.SearchViewModel
import com.example.repofinder.ui.viewmodel.SearchViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: RepoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        adapter = RepoAdapter { repository ->
            // Open Repo Details Activity
            val intent = Intent(this, RepoDetailsActivity::class.java)
            intent.putExtra("REPO", repository)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val searchView: SearchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchRepositories(it, 1) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        val apiService = RetrofitInstance.apiService
        val repositoryDao = RepositoryDatabase.getDatabase(this).repositoryDao()
        val repository = RepositoryRepository(apiService, repositoryDao)
        val factory = SearchViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[SearchViewModel::class.java]

        viewModel.repositories.observe(this, Observer {
            adapter.submitList(it)
        })
    }
}