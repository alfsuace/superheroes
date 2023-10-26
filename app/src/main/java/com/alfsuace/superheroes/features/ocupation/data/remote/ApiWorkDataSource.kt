package com.alfsuace.superheroes.features.ocupation.data.remote

import com.alfsuace.ejerciciovistas.app.Either
import com.alfsuace.ejerciciovistas.app.ErrorApp
import com.alfsuace.ejerciciovistas.app.left
import com.alfsuace.ejerciciovistas.app.right
import com.alfsuace.superheroes.features.superheroe.data.remote.SuperHeroApiModel
import com.alfsuace.superheroes.features.superheroe.data.remote.SuperHeroesApiService
import com.alfsuace.superheroes.features.superheroe.data.remote.toModel
import com.alfsuace.superheroes.features.superheroe.domain.SuperHero
import com.alfsuace.superheroes.features.superheroe.domain.Work
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class ApiWorkDataSource {

    private val baseUrl = "https://dam.sitehub.es/api-curso/superheroes/"


    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: WorkApiService = retrofit.create(WorkApiService::class.java)

    suspend fun getOccupation(id: String): Either<ErrorApp, Work> {
        try {
            val response: Response<WorkApiModel> = service.getWork(id)
            return if (response.isSuccessful) {
                response.body()!!.toModel(id).right()
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