package com.example.mobileapptest.ui.registration

/**
 * Created by Kevel on 4/17/2023.
 */
sealed class RegistrationState{
    object Loading: RegistrationState()
    object Idle: RegistrationState()
    data class Error(val message: String): RegistrationState()
}
