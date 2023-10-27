package com.alfsuace.superheroes.features.superheroe.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.alfsuace.superheroes.R
import com.alfsuace.superheroes.app.ErrorApp
import com.alfsuace.superheroes.app.serialization.GsonSerialization
import com.alfsuace.superheroes.databinding.ActivitySuperHeroBinding
import com.alfsuace.superheroes.features.superheroe.data.SuperHeroDataRepository
import com.alfsuace.superheroes.features.superheroe.data.local.SuperHeroLocalDataSource
import com.alfsuace.superheroes.features.superheroe.data.remote.ApiSuperHeroDataSource
import com.alfsuace.superheroes.features.superheroe.domain.GetSuperHeroUseCase
import com.alfsuace.superheroes.features.superheroe.domain.SuperHero

class SuperHeroActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuperHeroBinding

    val viewModel: SuperHeroViewModel by lazy {
        SuperHeroViewModel(
            GetSuperHeroUseCase(
                SuperheroDataRepository(
                    SuperHeroLocalDataSource(this, GsonSerialization()),
                    ApiSuperHeroDataSource()
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewBinding()
        setupObserver()
        viewModel.loadSuperHero()
    }

    private fun setupViewBinding() {
        binding = ActivitySuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupObserver() {
        val observer = Observer<SuperHeroViewModel.UiState> { uiState ->
            uiState.superHero?.apply {
                bindData(this)
            }
            uiState.errorApp?.let {
                showError(it)
            }
            if (uiState.isLoading) {
                showLoading()
            } else {
                hideLoading()
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun bindData(superHero: SuperHero) {
        binding.apply {
//            superHeroImagePreview.loadUrl(superHero.urlImage)
//            superHeroName.text = superHero.description
//            superHeroSitio.text = superHero.place
//            superHeroTotalPuntos.text = superHero.puntos.toString()
//            superHeroMediaPuntos.text = superHero.media.toString()
        }
    }

    private fun showError(error: ErrorApp) {
        binding.superHeroLayout.hide()
        binding.viewError.layoutError.show()
        when (error) {
            ErrorApp.UnknowErrorApp -> binding.viewError.labelMesaggeError.text =
                getString(R.string.label_unknow_error)

            ErrorApp.DataErrorApp -> TODO()
            ErrorApp.InternetConectionErrorApp -> TODO()
        }
    }

    private fun showLoading() {
        binding.skeletonLayout.showSkeleton()
    }

    private fun hideLoading() {
        binding.skeletonLayout.showOriginal()
    }

}