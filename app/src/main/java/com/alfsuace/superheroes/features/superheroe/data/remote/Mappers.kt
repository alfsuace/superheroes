package com.alfsuace.superheroes.features.superheroe.data.remote

import com.alfsuace.superheroes.features.description.data.remote.BiographyApiModel
import com.alfsuace.superheroes.features.ocupation.data.remote.WorkApiModel
import com.alfsuace.superheroes.features.superheroe.domain.Biography
import com.alfsuace.superheroes.features.superheroe.domain.SuperHero
import com.alfsuace.superheroes.features.superheroe.domain.Work

fun SuperHeroApiModel.toModel(): SuperHero =
    SuperHero(
        this.id,
        this.name,
        this.sm
    )
fun WorkApiModel.toModel(id: String): Work=
    Work(
        this.occupation
    )

fun BiographyApiModel.toModel(id: String): Biography=
    Biography(
        this.description
    )

