package com.bharath.rickyandmortyseries.repository

import com.bharath.rickyandmortyseries.datamodel.CharacterModel
import com.bharath.rickyandmortyseries.datamodel.CharacterPageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BaseApi {

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<CharacterModel>

    @GET("character")
    suspend fun getCharacterList(@Query("page") pageIndex: Int): Response<CharacterPageResponse>
}