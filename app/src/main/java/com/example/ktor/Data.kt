package com.example.ktor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("login")
    val login: String = "",
    @SerialName("avatar_url")
    val avatar_url: String = "",
)
