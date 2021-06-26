package com.bharath.rickyandmortyseries.datamodel

data class CharacterPageResponse(
    val info: Info = Info(),
    val results: List<CharacterModel> = emptyList()
){
    data class Info(
        val count: Int = 0,
        val pages: Int = 0,
        val next: String? = null,
        val prev: String? = null
    )
}
