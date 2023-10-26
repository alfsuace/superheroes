package com.alfsuace.superheroes.features.ocupation.data.remote

import com.google.gson.annotations.SerializedName

data class WorkApiModel (
    @SerializedName("occupation") val occupation: String
)