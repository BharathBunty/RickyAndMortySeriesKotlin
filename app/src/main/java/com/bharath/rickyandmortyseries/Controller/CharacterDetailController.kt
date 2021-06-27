package com.bharath.rickyandmortyseries.Controller

import com.airbnb.epoxy.EpoxyController
import com.bharath.rickyandmortyseries.R
import com.bharath.rickyandmortyseries.databinding.ModelCharacterDetailsDataPointBinding
import com.bharath.rickyandmortyseries.databinding.ModelCharacterDetailsHeaderBinding
import com.bharath.rickyandmortyseries.databinding.ModelCharacterDetailsImageBinding
import com.bharath.rickyandmortyseries.datamodel.CharacterModel
import com.dmp.simplemorty.epoxy.LoadingEpoxyModel
import com.dmp.simplemorty.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class CharacterDetailController: EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if(field){
                requestModelBuild()
            }
        }

    var characterDetailResponse: CharacterModel? = null
        set(value) {
            field = value
            if(field != null){
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {

        if(isLoading){
           LoadingEpoxyModel().id("loading_state").addTo(this)
           return
        }

        if(characterDetailResponse == null){
            return
        }

        // step - 1 : adding header to the screen
        CharacterNameModel(
            name = characterDetailResponse!!.name,
            gender = characterDetailResponse!!.gender,
            status = characterDetailResponse!!.status
        ).id("name").addTo(this)

        // step - 2 : adding image to the screen
        CharacterImageModel(characterDetailResponse!!.image).id("image").addTo(this)

        // step - 3 : adding info of the character
        CharacterInfoModel(
            title = "Origin",
            description = characterDetailResponse!!.origin.name
        ).id("origin").addTo(this)

        CharacterInfoModel(
            title = "Species",
            description = characterDetailResponse!!.species
        ).id("species").addTo(this)


    }


    data class CharacterNameModel(
        val name: String,
        val gender: String,
        val status: String
    ): ViewBindingKotlinModel<ModelCharacterDetailsHeaderBinding>(R.layout.model_character_details_header){
        override fun ModelCharacterDetailsHeaderBinding.bind() {
            nameTextView.text = name
            aliveTextView.text = status
            if(gender.equals("male",true)){
                genderImageView.setImageResource(R.drawable.ic_male_24)
            }else{
                genderImageView.setImageResource(R.drawable.ic_female_24)
            }
        }
    }

    data class CharacterImageModel(
        val image: String
    ):ViewBindingKotlinModel<ModelCharacterDetailsImageBinding>(R.layout.model_character_details_image){
        override fun ModelCharacterDetailsImageBinding.bind() {
            Picasso.get().load(image).into(headerImageView)
        }
    }

    data class CharacterInfoModel(
        val title: String,
        val description: String
    ):ViewBindingKotlinModel<ModelCharacterDetailsDataPointBinding>(R.layout.model_character_details_data_point){
        override fun ModelCharacterDetailsDataPointBinding.bind() {
            labelTextView.text = title
            textView.text = description
        }
    }
}