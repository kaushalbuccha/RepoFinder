package com.example.repofinder.repository

import androidx.lifecycle.LiveData
import com.example.repofinder.api.ApiService
import com.example.repofinder.db.RepoDao
import com.example.repofinder.db.RepositoryEntity
import com.example.repofinder.model.Repository

class RepositoryRepository(private val apiService: ApiService, private val repoDao: RepoDao) {

    suspend fun searchRepositories(query: String, page: Int): List<Repository> {
        return apiService.searchRepositories(query, page).items
    }

    suspend fun saveRepositoriesOffline(repos: List<RepositoryEntity>) {
        repoDao.insertAll(repos)
    }

    fun getSavedRepositories(): LiveData<List<RepositoryEntity>> {
        return repoDao.getRepositories()
    }
}