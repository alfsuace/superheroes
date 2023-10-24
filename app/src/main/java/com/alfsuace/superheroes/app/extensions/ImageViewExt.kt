package com.alfsuace.ejerciciovistas.app.extensions

import android.widget.ImageView
import com.alfsuace.superheroes.R
import com.bumptech.glide.Glide

    fun ImageView.loadUrl(url: String) {
        Glide.with(this.context)
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(this)
    }
