package tech.nouveta.demo.utils

data class AuthState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null
)