package com.alfsuace.superheroes.features.superheroe.domain

import com.alfsuace.superheroes.app.Either
import com.alfsuace.superheroes.app.ErrorApp
import kotlinx.coroutines.CoroutineDispatcher

class GetSuperHeroUseCase(
    private val superHeroRepository: SuperHeroRepository,
    private val biographyRepository: BiographyRepository,
    private val workRepository: WorkRepository
    ) {
    suspend operator fun invoke(): Either<ErrorApp, List<SuperHero>> {
        return superHeroRepository.obtainAll().map { superHeroes ->
            superHeroes.map{ superHero ->
                val biography = biographyRepository.getBiography(superHero.id)
                val work = workRepository.getWork(superHero.id)
                Person(superHero, biography.get(), work.get())
            }
        }
    }
    data class Person(
        val superHero: SuperHero,
        val biography: Biography,
        val work: Work
    )
}