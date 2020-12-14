package com.example.movies_mobile2you.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entities.Genres
import com.example.domain.entities.Movie
import com.example.movies_mobile2you.R
import com.example.movies_mobile2you.databinding.ActivityMainBinding
import com.example.movies_mobile2you.extension.visible
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.contentView
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var homeMovieAdapter: HomeMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupRecyclerView()
        setupViewModel()
        viewModel.handle(HomeIntent.StartData)
    }

    private fun setupRecyclerView() = with(binding.recyclerViewMoviesSimilar) {
        layoutManager = LinearLayoutManager(context)
        adapter = homeMovieAdapter
    }

    private fun setupViewModel() {
        viewModel.state.observe(this) { state ->
            when (state) {
                is HomeState.Loading -> {
                    setVisibilities(progressBar = state.loading)
                }
                is HomeState.SuccessDetail -> {
                    bindMovie(state.movie)
                }
                is HomeState.SuccessSimilarMovies -> {
                    bindSimilarMovies(state.similarMovies, state.genres)
                }
                is HomeState.Error -> {
                    retrySnackBar()
                }
            }
        }

    }

    private fun bindMovie(movie: Movie) {
        binding.movie = movie
        setVisibilities(
            imgHeart = true,
            txtLike = true,
            imgStar = true,
            txtPopularity = true,
            chkLike = true,
            progressBar = false
        )
    }

    private fun bindSimilarMovies(moviesSimilar: List<Movie>, genres: List<Genres>) {
        homeMovieAdapter.genres = genres
        homeMovieAdapter.movies = moviesSimilar.toMutableList()
    }

    private fun retrySnackBar() {
        contentView?.let {
            Snackbar.make(it, R.string.texto_erro_internet_snack, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.texto_tente_novamente) {
                    viewModel.handle(HomeIntent.StartData)
                }.show()
        }
    }

    private fun setVisibilities(
        imgHeart: Boolean = false,
        txtLike: Boolean = false,
        imgStar: Boolean = false,
        txtPopularity: Boolean = false,
        chkLike: Boolean = false,
        progressBar: Boolean = false
    ) {
        binding.imageHeart.visible(imgHeart)
        binding.textLikes.visible(txtLike)
        binding.imageStar.visible(imgStar)
        binding.textPopularity.visible(txtPopularity)
        binding.likeIcon.visible(chkLike)
        binding.likeIcon.visible(chkLike)
        binding.progressHome.visible(progressBar)
    }

}