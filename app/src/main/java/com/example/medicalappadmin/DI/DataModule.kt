package com.example.medicalappadmin.DI

import com.example.medicalappadmin.Data_Layer.ApiService
import com.example.medicalappadmin.Data_Layer.BASE_URL
import com.example.medicalappadmin.Repo.Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Provides
    @Singleton
    fun apiProvider() : ApiService{
        return Retrofit.Builder().baseUrl(BASE_URL).client(
            OkHttpClient.Builder().build()
        ).addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }
    @Singleton
    @Provides
    fun ProvideRepo(apiService: ApiService):Repo {
        return Repo(apiService)
    }


}