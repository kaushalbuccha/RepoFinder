package com.example.repofinder.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repofinder.db.RepositoryEntity
import com.example.repofinder.model.Repository
import com.example.repofinder.repository.RepositoryRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: RepositoryRepository) : ViewModel() {

    private val _repositories = MutableLiveData<List<Repository>>()
    val repositories: LiveData<List<Repository>> get() = _repositories

    fun searchRepositories(query: String, page: Int) {
        viewModelScope.launch {
            val repos = repository.searchRepositories(query, page)
            _repositories.postValue(repos)
            if (page == 1) repository.saveRepositoriesOffline(repos.map {
                RepositoryEntity(it.id, it.name, it.description, it.html_url, it.owner.login, it.owner.avatar_url)
            }.take(15))
        }
    }

    fun getSavedRepositories(): LiveData<List<RepositoryEntity>> {
        return repository.getSavedRepositories()
    }
}