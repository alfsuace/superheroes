package com.alfsuace.superheroes.features.description.data.local

import android.content.Context
import com.alfsuace.superheroes.app.Either
import com.alfsuace.superheroes.app.ErrorApp
import com.alfsuace.superheroes.app.left
import com.alfsuace.superheroes.app.right
import com.alfsuace.superheroes.app.serialization.JsonSerialization
import com.alfsuace.superheroes.features.superheroe.domain.Biography
import com.google.gson.Gson

class BiographyLocalDataSource(
    private val context: Context,
    private val serialization: JsonSerialization
) {

    val sharedPref = context.getSharedPreferences("biography", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun getBiographies(): Either<ErrorApp, List<Biography>> {
        return try {
            val jsonBiography = sharedPref.all as Map<String, String>

            val biographies = jsonBiography.values.map {
                serialization.fromJson(it, Biography::class.java)
            }

            return biographies.right()
        } catch (ex: Exception) {
            return ErrorApp.UnknowErrorApp.left()
        }
    }

    fun getBiography(id: Int): Either<ErrorApp, Biography> {
        val jsonBiography = sharedPref.getString(id.toString(), null)
        jsonBiography?.let {
            return serialization.fromJson(it, Biography::class.java).right()
        }
        return ErrorApp.UnknowErrorApp.left()
    }

    fun saveBiography(input: Biography): Either<ErrorApp, Boolean> {
        return try {
            with(sharedPref.edit()) {
                val biography = Biography(
                    input.id,
                    input.description
                )
                val jsonBiography = gson.toJson(
                    biography,
                    Biography::class.java
                )
                putString(biography.id, jsonBiography)
                apply()
            }
            true.right()
        } catch (ex: Exception) {
            ErrorApp.UnknowErrorApp.left()
        }
    }
}
