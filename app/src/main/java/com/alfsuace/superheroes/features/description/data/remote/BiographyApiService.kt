package com.alfsuace.superheroes.features.description.data.remote

import com.alfsuace.superheroes.features.ocupation.data.remote.WorkApiModel
import com.alfsuace.superheroes.features.superheroe.data.remote.SuperHeroApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BiographyApiService {
    //https://dam.sitehub.es/api-curso/superheroes/biography/1.json
    @GET("work/{idHero}.json")
    suspend fun getBiography(@Path("idHero") idHeroe: String) : Response<BiographyApiModel>
}