package com.bharath.rickyandmortyseries.pagingmodel

import androidx.paging.PageKeyedDataSource
import com.bharath.rickyandmortyseries.datamodel.CharacterModel
import com.bharath.rickyandmortyseries.repository.NetworkCalls
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CharacterListPagingResponse(
    private val coroutineScope: CoroutineScope,
    private val networkCall: NetworkCalls
): PageKeyedDataSource<Int , CharacterModel>() {

    /* update paging library to Paging3 */

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CharacterModel>) {
        coroutineScope.launch {
            val pageResponse = networkCall.getCharacterList(params.key)
            if(pageResponse == null){
                callback.onResult(emptyList(),null)
                return@launch
            }
            callback.onResult(pageResponse.results, getPageIndexFromString(pageResponse.info.next))
        }
    }

    private fun getPageIndexFromString(next: String?): Int? {
        return next?.split("?page=")?.get(1)?.toInt()
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CharacterModel>
    ) {
        coroutineScope.launch {
            val pageResponse = networkCall.getCharacterList(1)
            if(pageResponse == null){
                callback.onResult(emptyList(),null,null)
                return@launch
            }
            callback.onResult(pageResponse.results,null,getPageIndexFromString(pageResponse.info.next))
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CharacterModel>) {
        // Nothing to do
    }

}