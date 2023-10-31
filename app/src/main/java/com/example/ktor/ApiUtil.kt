package com.example.ktor

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class ApiUtil(private val client: HttpClient) {
    suspend fun getAllUsers(): List<Data> {
        return client.get("https://api.github.com/users")
    }
}