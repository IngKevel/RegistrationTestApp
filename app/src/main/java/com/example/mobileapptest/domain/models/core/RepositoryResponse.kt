package com.example.mobileapptest.domain.models.core

/**
 * Created by Kevel on 4/15/2023.
 */

/**
 * The sealed class are useful to have a control of values, much better the enum class
 */

sealed class RepositoryResponse<out T> {
    data class Success<T>(val data: T): RepositoryResponse<T>()
    data class Error(val code: Int, val message:String, val throwable: Exception? = null): RepositoryResponse<Nothing>()
}