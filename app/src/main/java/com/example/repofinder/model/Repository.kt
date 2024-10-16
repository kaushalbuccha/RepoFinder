package com.example.repofinder.model

import java.io.Serializable

data class Repository(
    val id: Long,
    val name: String,
    val description: String?,
    val html_url: String,
    val owner: Owner
): Serializable