package com.example.ktor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataContainer(
    @SerialName("data")
    val data: List<Data>
)
