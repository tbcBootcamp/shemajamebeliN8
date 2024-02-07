package com.example.shemajamebelin8.di

import com.example.shemajamebelin8.BuildConfig
import com.example.shemajamebelin8.data.global.common.HandleResponse
import com.example.shemajamebelin8.data.global.service.CitiesApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    @Singleton
    fun provideHandleResponse(): HandleResponse = HandleResponse()


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val httpClint = OkHttpClient.Builder()
        if (BuildConfig.DEBUG){
            httpClint.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return httpClint.build()
    }

    @Provides
    @Singleton
    fun provideCitiesRetrofitClient(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideCitiesApiService(retrofit: Retrofit): CitiesApi {
        return retrofit.create(CitiesApi::class.java)
    }
}