package com.example.mobileapptest.data.network

import com.example.mobileapptest.data.network.models.request.RegistrationDTORequest
import com.example.mobileapptest.data.network.models.response.RegistrationDTOResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by Kevel on 4/16/2023.
 */

/**
 * It is always important to use interfaces to prevent classes from being dependent on other classes.
 */
interface IRegistrationAPI {

    @Headers("Content-Type:application/json")
    @POST("getRegistrationFields")
    suspend fun getRegistration(
        @Body body: RegistrationDTORequest
    ): Response<RegistrationDTOResponse>
}