package com.bharath.rickyandmortyseries.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.bharath.rickyandmortyseries.datamodel.CharacterModel
import com.bharath.rickyandmortyseries.pagingmodel.CharacterListDataSourceFactory
import com.bharath.rickyandmortyseries.repository.NetworkCalls

class GetCharacterListViewModel: ViewModel() {

    private val networkCalls = NetworkCalls()

    private val pageListConfig: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(20)
        .setPrefetchDistance(2)
        .build()

    private val dataSourceFactory = CharacterListDataSourceFactory(viewModelScope , networkCalls)

    val characterListLiveData: LiveData<PagedList<CharacterModel>> = LivePagedListBuilder(
        dataSourceFactory , pageListConfig
    ).build()

}