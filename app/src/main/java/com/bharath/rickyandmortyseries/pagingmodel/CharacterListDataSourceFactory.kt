package com.bharath.rickyandmortyseries.pagingmodel

import androidx.paging.DataSource
import com.bharath.rickyandmortyseries.datamodel.CharacterModel
import com.bharath.rickyandmortyseries.repository.NetworkCalls
import kotlinx.coroutines.CoroutineScope

class CharacterListDataSourceFactory(
    private val coroutineScope: CoroutineScope,
    private val networkCalls: NetworkCalls
): DataSource.Factory<Int, CharacterModel>() {

    override fun create(): DataSource<Int, CharacterModel> {
        return CharacterListPagingResponse(coroutineScope , networkCalls)
    }
}