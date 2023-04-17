package com.example.mobileapptest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mobileapptest.data.network.IRegistrationAPI
import com.example.mobileapptest.data.network.RegistrationApiRepository
import com.example.mobileapptest.data.network.models.request.Data
import com.example.mobileapptest.data.network.models.request.RegistrationDTORequest
import com.example.mobileapptest.domain.models.core.RepositoryResponse
import com.example.mobileapptest.domain.utils.LOGIN_PASSWORD
import com.example.mobileapptest.domain.utils.LOGIN_USER
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RepositoryTest {

    private val mockWebServer = MockWebServer().apply {
        url("/")
        dispatcher = myDispatcher
    }

    private val iRegistrationAPI = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IRegistrationAPI::class.java)

    private val testRepository = RegistrationApiRepository(iRegistrationAPI)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    private val request = RegistrationDTORequest(
        login = LOGIN_USER,
        password = LOGIN_PASSWORD,
        data = Data(new_registration = true)
    )

    @Test
    fun `Registration fields is obtained correctly`() {
        runBlocking {

            when(val registration = testRepository.getRegistration(request)){
                is RepositoryResponse.Error -> {

                }
                is RepositoryResponse.Success -> {
                    assertEquals(12, registration.data.size)
                }
            }
        }
    }

    @Test
    fun `All visible fields have order parameter`() {
        runBlocking {

            when(val registration = testRepository.getRegistration(request)) {
                is RepositoryResponse.Error -> {

                }
                is RepositoryResponse.Success -> {
                    registration.data.forEach { field ->
                        if (field.visible) {
                            assertNotEquals(null, field.order)
                        }
                    }
                }
            }
        }
    }
}

val myDispatcher: Dispatcher = object: Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/getRegistrationFields" -> MockResponse().apply { addResponse("get_registration_response.json") }
            else -> MockResponse().setResponseCode(404)
        }
    }
}

fun MockResponse.addResponse(filePath: String): MockResponse {
    val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
    val source = inputStream?.source()?.buffer()
    source?.let {
        setResponseCode(200)
        setBody(it.readString(StandardCharsets.UTF_8))
    }
    return this
}