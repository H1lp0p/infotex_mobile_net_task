package com.example.domain.reposiotories

import com.example.domain.models.User

//Making repository interface. All for SOLID structure...Got it? It's fun
interface UserRepository {
    suspend fun getUsers(): List<User>
    suspend fun getUserById(id: Int): User
}