package com.example.mobileapptest.domain.models

import com.example.mobileapptest.data.network.models.response.Values

/**
 * Created by Kevel on 4/15/2023.
 */

data class FieldModel(
    val name: String,
    val visible: Boolean,
    val order: Int,
    val maxLength: Int? = null,
    val regex: String? = null,
    val valuesGender: List<String> = listOf(),
    val valuesLanguage: Values? = null
)
