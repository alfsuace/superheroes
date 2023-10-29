package com.alfsuace.superheroes.features.superheroe.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfsuace.superheroes.app.ErrorApp
import com.alfsuace.superheroes.features.superheroe.domain.GetSuperHeroUseCase
import com.alfsuace.superheroes.features.superheroe.domain.SuperHero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuperHeroViewModel(
    private val getSuperHeroUseCase: GetSuperHeroUseCase
) : ViewModel() {
    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadSuperHero() {
        viewModelScope.launch(Dispatchers.IO) {
            getSuperHeroUseCase.invoke().fold(
                { responseError(it) },
                { responseGetSuperHeroSuccess(it) }
            )
        }
    }

    private fun responseGetSuperHeroSuccess(it: List<GetSuperHeroUseCase.Person>) {
        _uiState.postValue(UiState(person = it))
    }

    private fun responseError(it: ErrorApp) {
        _uiState.postValue(UiState(errorApp = it))
    }

    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val person: List<GetSuperHeroUseCase.Person>? = null
    )

}
