package com.alfsuace.superheroes.features.superheroe.domain


data class SuperHero(
    val id: String,
    val name: String,
    val sm: String
)

data class Work(
    val occupation: String
)

data class Biography(
    val description: String
)