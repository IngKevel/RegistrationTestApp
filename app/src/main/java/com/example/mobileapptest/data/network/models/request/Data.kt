package com.example.mobileapptest.data.network.models.request

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("new-registration")
    val new_registration: Boolean
)