package com.alfsuace.superheroes.features.ocupation.data.remote

import com.alfsuace.superheroes.features.superheroe.data.remote.SuperHeroApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WorkApiService {
    //https://dam.sitehub.es/api-curso/superheroes/work/1.json
    @GET("work/{idHero}.json")
    suspend fun getWork(@Path("idHero") idHeroe: String) : Response<WorkApiModel>

}