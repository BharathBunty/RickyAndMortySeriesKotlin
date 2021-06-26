package com.bharath.rickyandmortyseries.repository

import com.bharath.rickyandmortyseries.datamodel.CharacterModel
import com.bharath.rickyandmortyseries.datamodel.CharacterPageResponse
import retrofit2.Response

class ApiClient(
   val baseApi: BaseApi
   ) {

    private inline fun <T> safeCall(apiCall: () -> Response<T>): NetworkResponseHelper<T>{
        return try{
            NetworkResponseHelper.success(apiCall.invoke())
        }catch (exception: Exception){
            NetworkResponseHelper.failure(exception)
        }
    }

    suspend fun getCharacterById(characterId: Int): NetworkResponseHelper<CharacterModel>{
        return safeCall { baseApi.getCharacterById(characterId) }
    }

    suspend fun getCharacterList(pageIndex: Int): NetworkResponseHelper<CharacterPageResponse>{
        return safeCall { baseApi.getCharacterList(pageIndex) }
    }

}
