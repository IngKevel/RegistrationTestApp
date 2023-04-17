package com.example.mobileapptest.domain.contracts

import com.example.mobileapptest.data.network.models.request.RegistrationDTORequest
import com.example.mobileapptest.domain.models.FieldModel
import com.example.mobileapptest.domain.models.core.RepositoryResponse

/**
 * Created by Kevel on 4/16/2023.
 */

/**
 * It is always important to use interfaces to prevent classes from being dependent on other classes.
 * It also helps us perform unit tests.
 */
interface IRegistrationRepository {

    suspend fun getRegistration(request: RegistrationDTORequest): RepositoryResponse<List<FieldModel>>
}