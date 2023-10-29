package com.alfsuace.superheroes.features.superheroe.domain


data class SuperHero(
    val id: String,
    val name: String,
    val sm: String
)

data class Work(
    val id: String,
    val occupation: String
)

data class Biography(
    val id: String,
    val description: String
)