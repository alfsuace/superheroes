package com.alfsuace.superheroes.features.superheroe.domain

import com.alfsuace.superheroes.app.Either
import com.alfsuace.superheroes.app.ErrorApp
import kotlinx.coroutines.CoroutineDispatcher

class GetSuperHeroUseCase(private val repository: SuperHeroRepository) {
    suspend operator fun invoke(io: CoroutineDispatcher): Either<ErrorApp, List<SuperHero>> {
        return repository.obtainAll()
    }
}