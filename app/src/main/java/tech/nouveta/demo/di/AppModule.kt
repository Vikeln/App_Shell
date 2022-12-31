package tech.nouveta.demo.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.nouveta.demo.data.remote.APIService
import tech.nouveta.demo.data.repository.AuthRepository
import tech.nouveta.demo.utils.Constants.BASE_URL
import tech.nouveta.demo.utils.Constants.SHARED_PREF_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideApiService(): APIService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(APIService::class.java)
    }

    @Provides
    @Singleton
    fun provideMainRepository(
        apiService: APIService,
        preferences: SharedPreferences
    ): AuthRepository {
        return AuthRepository(apiService, preferences)
    }


    @Provides
    @Singleton
    fun provideSharedPref(application: Application): SharedPreferences {
        return application.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }
}