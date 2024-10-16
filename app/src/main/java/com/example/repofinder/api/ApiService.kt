package com.example.repofinder.api

import com.example.repofinder.model.Contributor
import com.example.repofinder.model.RepoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 10
    ): RepoSearchResponse

    @GET("repos/{owner}/{repo}/contributors")
    suspend fun getContributors(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): List<Contributor>
}