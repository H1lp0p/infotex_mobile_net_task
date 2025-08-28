package com.example.domain.reposiotories

import com.example.domain.models.User

interface UserRepository {
    suspend fun getUsers(): List<User>
    suspend fun getUserById(id: Int): User
}