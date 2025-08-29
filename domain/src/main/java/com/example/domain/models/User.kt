package com.example.domain.models

// Main model for this task
data class User (
    val id: Int,
    val name: String,
    val userName: String,
    val email: String,

    val address: Address,

    val company: Company
)