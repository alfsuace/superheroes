package com.alfsuace.superheroes.features.description.data.remote

import com.alfsuace.superheroes.features.superheroe.domain.Biography
import com.google.gson.annotations.SerializedName

data class BiographyApiModel (
    @SerializedName("fullname") val description:String
        )