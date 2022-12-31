package tech.nouveta.demo.ui.screens.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tech.nouveta.demo.data.repository.AuthRepository
import tech.nouveta.demo.utils.AuthState
import tech.nouveta.demo.utils.Resource
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    private val _eventFlow = mutableStateOf(AuthState())
    val eventFlow: State<AuthState> = _eventFlow

    init {
        Timber.d("init called")
        viewModelScope.launch {
            val result: Resource<String?> = authRepository.autoLogin()

            Timber.d("Result: ${result.message}")
            when (result) {
                is Resource.Success -> {
                    Timber.d("success Called")
                    _eventFlow.value = eventFlow.value.copy(successMessage = result.message)
                    _eventFlow.value = eventFlow.value.copy(isSuccessful = true)
                }
                is Resource.Failure -> {
                    Timber.d("failure Called")
                    _eventFlow.value = eventFlow.value.copy(successMessage = result.message)
                    _eventFlow.value = eventFlow.value.copy(isSuccessful = false)
                }
                else -> {}
            }
        }
    }
}