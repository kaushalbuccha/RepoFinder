package com.example.repofinder.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repositories")
data class RepositoryEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String?,
    val html_url: String,
    val ownerLogin: String,
    val ownerAvatarUrl: String
)