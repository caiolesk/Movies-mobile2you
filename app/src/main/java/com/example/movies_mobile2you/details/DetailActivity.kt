package com.example.movies_mobile2you.details

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.domain.entities.Movie
import com.example.movies_mobile2you.R
import com.example.movies_mobile2you.databinding.ActivityDetailBinding
import org.jetbrains.anko.intentFor

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.lifecycleOwner = this
        binding.movie = intent.getSerializableExtra(EXTRA_MOVIE) as Movie
    }

    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }
}

fun Context.createDetailActivityIntent(movie: Movie) = intentFor<DetailActivity>(
    DetailActivity.EXTRA_MOVIE to movie
)