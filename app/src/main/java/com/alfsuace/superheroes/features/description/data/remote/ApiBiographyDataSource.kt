package com.alfsuace.superheroes.features.description.data.remote

import com.alfsuace.superheroes.app.Either
import com.alfsuace.superheroes.app.ErrorApp
import com.alfsuace.superheroes.app.left
import com.alfsuace.superheroes.app.right
import com.alfsuace.superheroes.features.superheroe.data.remote.toModel
import com.alfsuace.superheroes.features.superheroe.domain.Biography
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class ApiBiographyDataSource {

    private val baseUrl = "https://dam.sitehub.es/api-curso/superheroes/"


    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: BiographyApiService = retrofit.create(BiographyApiService::class.java)

    suspend fun getOccupation(id: String): Either<ErrorApp, Biography> {
        try {
            val response: Response<BiographyApiModel> = service.getBiography(id)
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