package com.alfsuace.superheroes.features.superheroe.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfsuace.superheroes.R
import com.alfsuace.superheroes.features.superheroe.domain.GetSuperHeroUseCase

class SuperHeroAdapter() : RecyclerView.Adapter<SuperHeroViewHolder>() {

    private val dataList: MutableList<GetSuperHeroUseCase.Person> = mutableListOf()

    fun setData(superheroes: List<GetSuperHeroUseCase.Person>) {
        dataList.clear()
        addDataList(superheroes)
    }

    fun addDataList(superheroes: List<GetSuperHeroUseCase.Person>) {
        dataList.addAll(superheroes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_super_hero, parent, false)
        return SuperHeroViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}
