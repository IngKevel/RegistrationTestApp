package com.example.mobileapptest.data.network.models.request

data class RegistrationDTORequest(
    val `data`: Data,
    val login: String,
    val password: String
)