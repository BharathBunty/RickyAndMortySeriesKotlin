package com.bharath.rickyandmortyseries.repository

import com.bharath.rickyandmortyseries.datamodel.CharacterModel
import com.bharath.rickyandmortyseries.datamodel.CharacterPageResponse

class NetwokCalls {

    suspend fun getCharacterById(characterId: Int): CharacterModel? {
        val request = BaseService().apiClient.getCharacterById(characterId)
        if(request.failed || !request.isSuccessful ){
            return null
        }
        return request.body
    }

    suspend fun getCharacterList(pageIndex: Int): CharacterPageResponse? {
        val request = BaseService().apiClient.getCharacterList(pageIndex)
        if(request.failed || !request.isSuccessful ){
            return null
        }
        return request.body
    }

}