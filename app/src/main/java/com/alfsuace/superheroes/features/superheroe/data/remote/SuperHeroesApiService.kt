package com.alfsuace.superheroes.features.superheroe.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface SuperHeroesApiService {
    // https://dam.sitehub.es/api-curso/superheroes/heroes.json
    @GET("heroes.json")
    suspend fun getSuperHeroes(): Response<List<SuperHeroApiModel>>
}
