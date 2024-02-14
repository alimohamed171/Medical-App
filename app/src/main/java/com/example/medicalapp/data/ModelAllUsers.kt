package com.example.medicalapp.data

data class ModelAllUsers(
    val `data`: List<UsersData>?,
    val message: String,
    val status: Int
)

data class UsersData(
    val avatar: String,
    val first_name: String,
    val id: Int,
    val type: String
)