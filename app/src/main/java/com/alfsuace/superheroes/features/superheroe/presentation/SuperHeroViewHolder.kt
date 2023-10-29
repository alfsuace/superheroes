package com.alfsuace.superheroes.features.superheroe.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alfsuace.superheroes.features.superheroe.domain.GetSuperHeroUseCase



class SuperHeroViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private lateinit var binding: ViewActivitySuperHeroBinding


    fun bind(superhero: GetSuperHeroUseCase.Person) {
        binding = ViewActivitySuperHeroBinding.bind(view)

        binding.apply {
            imagePreview.setUrl(superhero.superHero.images)
            superHeroName.text = superhero.superHero.name
            superHeroRealName.text = superhero.biography.fullName
            superHeroOccupation.text = superhero.work.occupation
        }
    }
}