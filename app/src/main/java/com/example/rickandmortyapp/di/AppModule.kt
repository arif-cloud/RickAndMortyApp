package com.example.rickandmortyapp.di

import com.example.rickandmortyapp.common.Constants
import com.example.rickandmortyapp.data.remote.RickAndMortyApi
import com.example.rickandmortyapp.data.repository.RickAndMortyRepositoryImpl
import com.example.rickandmortyapp.domain.repository.RickAndMortyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun provideRickAndMortyApi() : RickAndMortyApi{
        return Retrofit.Builder().
        baseUrl(Constants.BASE_URL).
        client(client).
        addConverterFactory(GsonConverterFactory.create()).
        build().
        create(RickAndMortyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRickAndMortyRepository(api: RickAndMortyApi) : RickAndMortyRepository {
        return RickAndMortyRepositoryImpl(api)
    }
}