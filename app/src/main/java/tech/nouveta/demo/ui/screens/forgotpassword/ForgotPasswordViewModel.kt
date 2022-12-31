package tech.nouveta.demo.ui.screens.forgotpassword

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tech.nouveta.demo.data.repository.AuthRepository
import tech.nouveta.demo.model.request.ForgotPasswordRequest
import tech.nouveta.demo.utils.AuthState
import tech.nouveta.demo.utils.Resource
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    //forgot Password
    private val _forgotPass = mutableStateOf(AuthState())
    val forgotPass: State<AuthState> = _forgotPass

    private val _emailState = mutableStateOf("")
    val emailState: State<String> = _emailState

    fun setEmail(value: String) {
        _emailState.value = value
    }

    //forgot password
    fun forgotPassword() {
        if (emailState.value.isBlank()) {
            "Email is null "
        } else {
            _forgotPass.value = AuthState(isLoading = true)
            viewModelScope.launch {

                val forgotPasswordRequest = ForgotPasswordRequest(emailState.value)

                when (val result = authRepository.forgotPassword(forgotPasswordRequest)) {
                    is Resource.Success -> {
                        _forgotPass.value = AuthState(isLoading = false, successMessage = result.data?.message, isSuccessful = true)

                    }
                    is Resource.Failure -> {
                        _forgotPass.value = AuthState(isLoading = false, error = result.message)

                    }
                    else -> {}
                }
            }

        }
    }
}