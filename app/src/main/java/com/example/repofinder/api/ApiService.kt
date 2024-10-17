package com.example.repofinder.api

import com.example.repofinder.model.Contributor
import com.example.repofinder.model.RepoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 10
    ): RepoSearchResponse

    @GET
    suspend fun getContributors(@Url contributorsUrl: String): List<Contributor>
}