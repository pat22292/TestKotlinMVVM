package com.gaur.pixabayimagesearch.di

import com.gaur.pixabayimagesearch.network.ApiService
import com.gaur.pixabayimagesearch.ui.components.MainRepository
import com.gaur.pixabayimagesearch.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }


    @Provides
    fun provideMainRepository(apiService: ApiService):MainRepository{
        return MainRepository(apiService = apiService)
    }


}