package tech.nouveta.demo.data.remote

import retrofit2.http.Body
import retrofit2.http.POST
import tech.nouveta.demo.model.request.ForgotPasswordRequest
import tech.nouveta.demo.model.responses.ItemResponse
import tech.nouveta.demo.model.responses.LoginResponse
import tech.nouveta.demo.ui.data.remote.request.LoginRequest
import tech.nouveta.demo.utils.Constants.FORGOT_PASSWORD_ENDPOINT
import tech.nouveta.demo.utils.Constants.LOGIN_ENDPOINT

interface APIService {

    //Login
    @POST(LOGIN_ENDPOINT)
    suspend fun loginUser(

        @Body loginRequest: LoginRequest
    ): LoginResponse

    @POST(FORGOT_PASSWORD_ENDPOINT)
    suspend fun forgotPassword(
        @Body forgotPasswordRequest: ForgotPasswordRequest
    ): ItemResponse



}