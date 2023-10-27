package com.alfsuace.superheroes.features.ocupation.data.remote

import com.google.gson.annotations.SerializedName

data class WorkApiModel (
    @SerializedName("id") val id: String,
    @SerializedName("occupation") val occupation: String
)