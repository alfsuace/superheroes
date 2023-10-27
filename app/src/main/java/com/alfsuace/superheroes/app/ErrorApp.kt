package com.alfsuace.superheroes.app

sealed class ErrorApp {
    object UnknowErrorApp : ErrorApp()
    object DataErrorApp: ErrorApp()
    object InternetConectionErrorApp: ErrorApp()
}
