package com.alfsuace.superheroes.features.superheroe.domain

import com.alfsuace.superheroes.app.Either
import com.alfsuace.superheroes.app.ErrorApp

interface SuperHeroRepository {
    suspend fun obtainAll(): Either<ErrorApp, List<SuperHero>>
}