package com.example.mobileapptest.ui.registration

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileapptest.data.network.models.request.Data
import com.example.mobileapptest.data.network.models.request.RegistrationDTORequest
import com.example.mobileapptest.domain.contracts.IRegistrationRepository
import com.example.mobileapptest.domain.models.FieldModel
import com.example.mobileapptest.domain.models.core.RepositoryResponse
import com.example.mobileapptest.domain.utils.LOGIN_PASSWORD
import com.example.mobileapptest.domain.utils.LOGIN_USER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Kevel on 4/15/2023.
 */

/**
 * We keep the private values inside the ViewModel but with the use of get() the View can display them.
 */

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registrationRepository: IRegistrationRepository
): ViewModel() {

    private val _fieldsList = mutableStateOf<List<FieldModel>>(listOf())
    val fieldsList: State<List<FieldModel>>
        get() = _fieldsList

    private val _validFields = mutableStateOf(false)
    val validFields: State<Boolean>
        get() = _validFields

    private val _registrationState = mutableStateOf<RegistrationState>(RegistrationState.Loading)
    val registrationState: State<RegistrationState>
        get() = _registrationState

    private val request = RegistrationDTORequest(
        login = LOGIN_USER,
        password = LOGIN_PASSWORD,
        data = Data(new_registration = true)
    )

    fun getRegistration() {
        viewModelScope.launch {
            _registrationState.value = RegistrationState.Loading
            val dataList: MutableList<FieldModel> = mutableListOf()
            when(val result = registrationRepository.getRegistration(request = request)) {
                is RepositoryResponse.Error -> {
                    _registrationState.value = RegistrationState.Error(result.message)
                }
                is RepositoryResponse.Success -> {
                    result.data.forEach { data ->
                        if (data.visible) {
                            dataList.add(data)
                        }
                    }
                    dataList.sortBy { it.order }
                    _fieldsList.value = dataList
                    _registrationState.value = RegistrationState.Idle
                }
            }
        }
    }

    /**
     * With this function we can indicate if any field does not follow the rule of its regex.
     */
    fun checkFields(valid: Boolean) {
        _validFields.value = valid
    }
}