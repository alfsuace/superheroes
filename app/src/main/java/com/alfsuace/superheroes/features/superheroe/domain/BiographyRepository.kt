package com.alfsuace.superheroes.features.superheroe.domain

import com.alfsuace.superheroes.app.Either
import com.alfsuace.superheroes.app.ErrorApp

interface BiographyRepository {
    suspend fun getBiography(id: String): Either<ErrorApp, Biography>
}