package com.alfsuace.superheroes.features.ocupation.data.local


import android.content.Context
import com.alfsuace.superheroes.app.Either
import com.alfsuace.superheroes.app.ErrorApp
import com.alfsuace.superheroes.app.left
import com.alfsuace.superheroes.app.right
import com.alfsuace.superheroes.app.serialization.JsonSerialization
import com.alfsuace.superheroes.features.superheroe.domain.Work
import com.google.gson.Gson


class WorkLocalDataSource(
    private val context: Context,
    private val serialization: JsonSerialization
) {

    val sharedPref = context.getSharedPreferences("work", Context.MODE_PRIVATE)
    private val gson = Gson()



    fun getWorks(): Either<ErrorApp, List<Work>> {
        return try {
            val jsonWork = sharedPref.all as Map<String, String>

            val works = jsonWork.values.map {
                serialization.fromJson(it, Work::class.java)
            }

            return works.right()
        } catch (ex: Exception) {
            return ErrorApp.UnknowErrorApp.left()
        }
    }

    fun getWork(id: Int): Either<ErrorApp, Work> {
        val jsonWork = sharedPref.getString(id.toString(), null)
        jsonWork?.let {
            return serialization.fromJson(it, Work::class.java).right()
        }
        return ErrorApp.UnknowErrorApp.left()
    }

    fun saveWork(input: Work): Either<ErrorApp, Boolean> {
        return try {
            with(sharedPref.edit()) {
                val work = Work(
                    input.id,
                    input.occupation
                )
                val jsonWork = gson.toJson(
                    work,
                    Work::class.java
                )
                putString(work.id, jsonWork)
                apply()
            }
            true.right()
        } catch (ex: Exception) {
            ErrorApp.UnknowErrorApp.left()
        }
    }
}
