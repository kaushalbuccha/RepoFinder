package com.example.repofinder.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<RepositoryEntity>)

    @Query("SELECT * FROM repositories")
    fun getRepositories(): LiveData<List<RepositoryEntity>>
}