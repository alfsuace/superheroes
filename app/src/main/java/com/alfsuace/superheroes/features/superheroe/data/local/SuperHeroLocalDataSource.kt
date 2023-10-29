package com.alfsuace.superheroes.features.superheroe.data.local


import android.content.Context
import com.alfsuace.superheroes.app.Either
import com.alfsuace.superheroes.app.ErrorApp
import com.alfsuace.superheroes.app.left
import com.alfsuace.superheroes.app.right
import com.alfsuace.superheroes.app.serialization.JsonSerialization
import com.alfsuace.superheroes.features.superheroe.domain.SuperHero
import com.google.gson.Gson


class SuperHeroLocalDataSource(
    private val context: Context,
    private val serialization: JsonSerialization
) {

    val sharedPref = context.getSharedPreferences("superHero", Context.MODE_PRIVATE)
    private val gson = Gson()



    fun getSuperHeroes(): Either<ErrorApp, List<SuperHero>> {
        return try {
            val jsonSuperHeroes=sharedPref.all as Map<String,String>

            val superHero=jsonSuperHeroes.values.map {
                serialization.fromJson(it,SuperHero::class.java)
            }

            return superHero.right()
        }catch (ex:Exception){
            return ErrorApp.UnknowErrorApp.left()
        }

    }

    fun getSuperHero(id: Int): Either<ErrorApp, SuperHero> {
        val jsonSuperHero = sharedPref.getString(id.toString(), null)
        jsonSuperHero?.let {
            return serialization.fromJson(it, SuperHero::class.java).right()
        }
        return ErrorApp.UnknowErrorApp.left()
    }

    fun saveSuperHero(input: SuperHero): Either<ErrorApp, Boolean> {
        return try {
            with(sharedPref.edit()) {

                val superHero = SuperHero(
                    input.id,
                    input.name,
                    input.sm
                )
                val jsonSuperHero = gson.toJson(
                    superHero,
                    SuperHero::class.java
                )
                putString(superHero.id, jsonSuperHero)
                apply()
            }
            true.right()
        } catch (ex: Exception) {
            ErrorApp.UnknowErrorApp.left()
        }
    }

}
