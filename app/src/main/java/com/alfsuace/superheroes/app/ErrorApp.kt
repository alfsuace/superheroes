package com.alfsuace.ejerciciovistas.app

sealed class ErrorApp {
    object UnknowErrorApp : ErrorApp()
    object DataErrorApp: ErrorApp()
    object InternetConectionErrorApp: ErrorApp()
}