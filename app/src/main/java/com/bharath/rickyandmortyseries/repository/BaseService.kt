package com.bharath.rickyandmortyseries.repository

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class BaseService {

    companion object{
        private const val BASE_URL = "https://rickyandmortyapi.com/api/"
        private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        private val httpInterceptorClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }).build()
    }

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpInterceptorClient)
            .build()
    }

    fun getBaseApi(): BaseApi{
        return getRetrofitInstance().create(BaseApi::class.java)
    }

    val apiClient = ApiClient(getBaseApi())


}