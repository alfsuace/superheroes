package com.alfsuace.superheroes.features.superheroe.data.remote

import com.google.gson.annotations.SerializedName

data class SuperHeroApiModel(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    //@SerializedName("biography") val description: String,
    @SerializedName("images") val sm: String
)

