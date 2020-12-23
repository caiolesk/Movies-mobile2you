package com.example.movies_mobile2you.home

import androidx.fragment.app.FragmentActivity
import com.example.domain.entities.Movie
import com.example.movies_mobile2you.details.createDetailActivityIntent
import javax.inject.Inject

class HomeRouter @Inject constructor(
    private val activity: FragmentActivity
) {

    fun routeToDetail(movie: Movie) {
        activity.startActivity(activity.createDetailActivityIntent(movie))
    }

}