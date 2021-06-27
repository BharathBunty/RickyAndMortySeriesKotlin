package com.bharath.rickyandmortyseries.Controller

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.bharath.rickyandmortyseries.R
import com.bharath.rickyandmortyseries.databinding.ModelCharacterListItemBinding
import com.bharath.rickyandmortyseries.databinding.ModelCharacterListTitleBinding
import com.bharath.rickyandmortyseries.datamodel.CharacterModel
import com.dmp.simplemorty.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso
import java.util.*

class CharacterListController(
    val onCharacterSelected: (Int) -> Unit
): PagedListEpoxyController<CharacterModel>() {


    override fun buildItemModel(currentPosition: Int, item: CharacterModel?): EpoxyModel<*> {
        return CharacterListItemModel(
            item!!.id,
            item.image,
            item.name,
            onCharacterSelected
        ).id(item.id)
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        if(models.isEmpty()){
            return
        }
        CharacterListTitleModel("Main Family").id("title").addTo(this)
        super.addModels(models)
        (models.subList(5,models.size) as List<CharacterListItemModel>).groupBy {
            it.name[0].uppercaseChar()
        }.forEach { item ->  CharacterListTitleModel(item.key.toString().uppercase(Locale.US))
            .id("title")
            .addTo(this)
            super.addModels(item.value)
        }
    }

    data class CharacterListItemModel(
        val characterId: Int,
        val imageUrl: String,
        val name: String,
        val onCharacterSelected: (Int) -> Unit
    ): ViewBindingKotlinModel<ModelCharacterListItemBinding>(R.layout.model_character_list_item) {

        override fun ModelCharacterListItemBinding.bind() {
            Picasso.get().load(imageUrl).into(characterImageView)
            characterNameTextView.text = name
            root.setOnClickListener {
                onCharacterSelected(characterId)
            }
        }
    }

    data class CharacterListTitleModel(
        val title: String
    ): ViewBindingKotlinModel<ModelCharacterListTitleBinding>(R.layout.model_character_list_title) {

        override fun ModelCharacterListTitleBinding.bind() {
            textView.text = title
        }

        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            return totalSpanCount
        }
    }
}