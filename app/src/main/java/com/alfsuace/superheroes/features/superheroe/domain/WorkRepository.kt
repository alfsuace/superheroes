package com.alfsuace.superheroes.features.superheroe.domain

import com.alfsuace.superheroes.app.Either
import com.alfsuace.superheroes.app.ErrorApp

interface WorkRepository {
    suspend fun getWork(id: String): Either<ErrorApp, Work>
}