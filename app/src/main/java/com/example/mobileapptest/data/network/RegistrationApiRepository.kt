package com.example.mobileapptest.data.network

import com.example.mobileapptest.data.network.models.request.RegistrationDTORequest
import com.example.mobileapptest.domain.contracts.IRegistrationRepository
import com.example.mobileapptest.domain.models.FieldModel
import com.example.mobileapptest.domain.models.core.RepositoryResponse
import javax.inject.Inject

/**
 * Created by Kevel on 4/16/2023.
 */

/**
 * This class shows SOLID principles very well, being a class that receives an interface and inherits another interface.
 */

class RegistrationApiRepository @Inject constructor(
    private val registrationAPI: IRegistrationAPI
    ): IRegistrationRepository {

    override suspend fun getRegistration(
        request: RegistrationDTORequest
    ): RepositoryResponse<List<FieldModel>> {
        return try {
            val result = registrationAPI.getRegistration(body = request)
            if (result.isSuccessful){
                RepositoryResponse.Success(result.body()?.toList() ?: listOf())
            } else {
                RepositoryResponse.Error(result.code(), result.errorBody()?.string() ?: "Unknown Error")
            }
        } catch (e: Exception) {
            RepositoryResponse.Error(-1, e.localizedMessage ?: "Unknown Error", e)
        }
    }
}