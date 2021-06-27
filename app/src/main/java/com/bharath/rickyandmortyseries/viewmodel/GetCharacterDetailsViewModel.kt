package com.bharath.rickyandmortyseries.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharath.rickyandmortyseries.datamodel.CharacterModel
import com.bharath.rickyandmortyseries.repository.NetworkCalls
import kotlinx.coroutines.launch

class GetCharacterDetailsViewModel: ViewModel() {

    private val networkCall = NetworkCalls()

    private val _characterByIdLiveData = MutableLiveData<CharacterModel?>()
    val characterByIdLiveData : LiveData<CharacterModel?> = _characterByIdLiveData

    fun getCharacterDetails(characterId: Int){
        viewModelScope.launch {
            val response = networkCall.getCharacterById(characterId)
            _characterByIdLiveData.postValue(response)
        }
    }

}