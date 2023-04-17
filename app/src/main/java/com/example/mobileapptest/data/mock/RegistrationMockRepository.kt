package com.example.mobileapptest.data.mock

import com.example.mobileapptest.data.network.models.request.RegistrationDTORequest
import com.example.mobileapptest.domain.contracts.IRegistrationRepository
import com.example.mobileapptest.domain.models.FieldModel
import com.example.mobileapptest.domain.models.core.RepositoryResponse
import javax.inject.Inject

/**
 * Created by Kevel on 4/17/2023.
 */
class RegistrationMockRepository @Inject constructor(): IRegistrationRepository {

    override suspend fun getRegistration(request: RegistrationDTORequest): RepositoryResponse<List<FieldModel>> {
        return RepositoryResponse.Success(
            listOf(
                FieldModel(name = "Field", visible = true, order = 3, maxLength = 20, regex = ""),
                FieldModel(name = "Field", visible = true, order = 4, maxLength = null, regex = ""),
                FieldModel(name = "Field", visible = false, order = 2, maxLength = 50, regex = null),
                FieldModel(name = "Field", visible = true, order = 1, maxLength = 40, regex = null)
            )
        )
    }
}