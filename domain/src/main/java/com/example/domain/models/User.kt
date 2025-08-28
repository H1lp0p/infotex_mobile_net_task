package com.example.domain.models

data class User (
    val id: Int,
    val name: String,
    val userName: String,
    val email: String,

    val address: Address,

    val company: Company
)