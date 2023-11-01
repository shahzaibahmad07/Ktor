package com.example.ktor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("first_name")
    val first_name: String = "",
    @SerialName("avatar")
    val avatar: String = "",
)
