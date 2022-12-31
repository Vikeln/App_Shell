package tech.nouveta.demo.data.repository

import android.content.SharedPreferences
import retrofit2.HttpException
import tech.nouveta.demo.data.remote.APIService
import tech.nouveta.demo.model.request.ForgotPasswordRequest
import tech.nouveta.demo.model.responses.ItemResponse
import tech.nouveta.demo.model.responses.LoginResponse
import tech.nouveta.demo.ui.data.remote.request.LoginRequest
import tech.nouveta.demo.utils.DateFormatter
import tech.nouveta.demo.utils.Resource
import timber.log.Timber
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

class AuthRepository(
    private val apiService: APIService,
    private val pref: SharedPreferences
) {
    //login
    suspend fun loginUser(loginRequest: LoginRequest): Resource<LoginResponse> {
        return try {
            Timber.d("Start login API call")
            val response = apiService.loginUser(loginRequest)
            Timber.d("End login API call")
            pref.edit()
                .putString("token", response.accessToken)
                .apply()

            pref.edit()
                .putLong("tokenExpiry", response.expiry)
                .apply()

            Resource.Success(response)

        } catch (e: IOException) {
            Timber.d("Network Error : ".plus(e.localizedMessage))
            return Resource.Failure("Oops! couldn't reach server, check your internet connection.")
        } catch (e: HttpException) {
            Timber.d("Network Error : ".plus(e.localizedMessage))
            if (e.code() == 401) {
                return Resource.Failure("Incorrect password, check your credential")
            }
            return Resource.Failure("Oops! something went wrong. Please try again")
        }
    }

    //forgot password
    suspend fun forgotPassword(forgotPasswordRequest: ForgotPasswordRequest): Resource<ItemResponse> {
        return try {
            Timber.d("start forgotPassword")
            val response = apiService.forgotPassword(forgotPasswordRequest)

            Timber.d(response.message)

            if (response.status.equals(true))
                Resource.Success(response)
            else
                Resource.Failure(response.message)

        } catch (e: Exception) {
            Timber.d("Exception forgotPassword")
            Timber.d(e.message)
            Timber.d(e.localizedMessage)
            return Resource.Failure(e.localizedMessage)
        }
    }

    fun autoLogin(): Resource<String?> {
        Timber.d("AutoLogin Called")
        return try {

            val tokenExpiry = pref.getLong("tokenExpiry", 0)
            if (tokenExpiry!=null){
                Timber.d("Found token expiry ".plus(tokenExpiry))
                if (checkTokenExpiry(tokenExpiry)) {
                   clearAndReturnFailure("Token Expired!")
                }else{
                    val token = pref.getString("token", null)
                    Timber.d(" token $token")
                    Timber.d("success")
                    Resource.Success(token)
                }
            }else
                clearAndReturnFailure("TokenExpiry could not be read")

        } catch (e: Exception) {
            return Resource.Failure("Unknown Error")
        }

    }

    private fun clearAndReturnFailure(message : String): Resource.Failure<String?> {
        pref.edit().clear().apply()
        Timber.d("failure")
       return Resource.Failure(message)
    }

    fun checkTokenExpiry(time: Long): Boolean {
        return Date() >  Date(time)
    }

}

