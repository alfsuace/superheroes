package com.alfsuace.superheroes.features.superheroe.data.remote

import com.alfsuace.superheroes.app.Either
import com.alfsuace.superheroes.app.ErrorApp
import com.alfsuace.superheroes.app.left
import com.alfsuace.superheroes.app.right
import com.alfsuace.superheroes.features.superheroe.domain.SuperHero

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class ApiSuperHeroDataSource {

    private val baseUrl = "https://dam.sitehub.es/api-curso/superheroes/"


        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: SuperHeroesApiService = retrofit.create(SuperHeroesApiService::class.java)

        suspend fun getAllSuperHeroes(): Either<ErrorApp, List<SuperHero>> {
            try {
                val response: Response<List<SuperHeroApiModel>> = service.getSuperHeroes()
                return if (response.isSuccessful) {
                    val heroes = response.body()!!.map {
                        it.toModel()
                    }
                    return heroes.right()
                } else {
                    ErrorApp.InternetConectionErrorApp.left()
                }
            } catch (ex: TimeoutException) {
                return ErrorApp.InternetConectionErrorApp.left()
            } catch (ex: UnknownHostException) {
                return ErrorApp.InternetConectionErrorApp.left()
            } catch (ex: Exception) {
                return ErrorApp.UnknowErrorApp.left()
            }
        }

}
