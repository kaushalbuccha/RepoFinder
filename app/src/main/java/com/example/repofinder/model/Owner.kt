package com.example.repofinder.model

import java.io.Serializable

data class Owner(
    val login: String,
    val avatar_url: String
): Serializable