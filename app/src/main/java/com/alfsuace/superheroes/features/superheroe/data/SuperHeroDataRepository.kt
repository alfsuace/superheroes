package com.alfsuace.superheroes.features.superheroe.data

import com.alfsuace.superheroes.app.Either
import com.alfsuace.superheroes.app.ErrorApp
import com.alfsuace.superheroes.app.right
import com.alfsuace.superheroes.features.superheroe.data.local.SuperHeroLocalDataSource
import com.alfsuace.superheroes.features.superheroe.data.remote.ApiSuperHeroDataSource
import com.alfsuace.superheroes.features.superheroe.domain.SuperHero
import com.alfsuace.superheroes.features.superheroe.domain.SuperHeroRepository

class SuperHeroDataRepository(
    private val localDataSource: SuperHeroLocalDataSource,
    private val remoteDataSource: ApiSuperHeroDataSource
) : SuperHeroRepository {
    override suspend fun obtainAll(): Either<ErrorApp, List<SuperHero>> {
        val localSuperHero = localDataSource.getSuperHeroes()
        return if (localSuperHero.isRight() && localSuperHero.getOrNull() != null) {
            localSuperHero
        } else {
            val remoteSuperHero = remoteDataSource.getAllSuperHeroes()
            remoteSuperHero
        }
    }
}
