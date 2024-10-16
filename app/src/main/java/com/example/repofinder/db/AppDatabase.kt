package com.example.repofinder.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RepositoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}