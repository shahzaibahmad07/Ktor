package com.example.ktor

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post

class ApiUtil(private val client: HttpClient) {
    suspend fun getAllUsers(): DataContainer {
        return client.get("https://reqres.in/api/users?page=2")
    }

    suspend fun getSingleUser(id: Int): Data {
        return client.get("https://reqres.in/api/users/$id")
    }
}