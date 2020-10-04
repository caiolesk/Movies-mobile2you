package com.example.movies_mobile2you.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entities.Genres
import com.example.domain.entities.Movie
import com.example.movies_mobile2you.R
import com.example.movies_mobile2you.adapter.MovieAdapter
import com.example.movies_mobile2you.databinding.ActivityMainBinding
import com.example.movies_mobile2you.extension.visible
import com.example.movies_mobile2you.viewmodel.MainViewModel
import com.example.movies_mobile2you.viewmodel.ViewState
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.contentView
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding : ActivityMainBinding
    private var movie: Movie? = null
    private var genres: List<Genres>? = null
    private val movieAdapter: MovieAdapter by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupRecyclerView()
        setupViewModel()
        initData()
    }

    private fun setupRecyclerView() = with(recyclerViewMoviesSimilar){
        layoutManager = LinearLayoutManager(context)
        adapter = movieAdapter
    }

    private fun setupViewModel(){
        viewModel.state.observe(this, { state ->
            when(state){
                is ViewState.Sucess ->{
                    movie = state.data

                    binding.movie = movie

                    setVisibilities(imgHeart = true,txtLike = true,imgStar = true, txtPopularity = true,chkLike = true)

                    viewModel.fetchGenresList(getString(R.string.api_key))

                    movie?.id?.let {
                        viewModel.fetchMoviesSimilar(getString(R.string.api_key),
                            it
                        )
                    }
                }
                is ViewState.Loading ->{
                }
                is ViewState.Failed ->{
                    retrySnackBar()
                }
            }
        })

        viewModel.stateMoviesSimilar.observe(this, { state ->
            when(state){
                is ViewState.Sucess ->{
                    var moviesSimilar = state.data

                    if(!genres.isNullOrEmpty()){
                        moviesSimilar.forEach { movie ->
                            var genresLinked = genres?.filter { m -> movie.genre_ids?.any { it  == m.id }!! }?.map {  c -> c.name }

                            movie.genre_names = genresLinked
                        }
                    }

                    movieAdapter.movies = moviesSimilar
                    movieAdapter.notifyDataSetChanged()
                }
                is ViewState.Loading ->{
                }
                is ViewState.Failed ->{
                    toast("Failed to fetch similar movies, try latter").show()
                }
            }
        })

        viewModel.stateGenres.observe(this, { state ->
            when(state){
                is ViewState.Sucess ->{
                    genres = state.data
                }
                is ViewState.Loading ->{
                }
                is ViewState.Failed ->{
                    toast("Failed to fetch genres list, try latter").show()
                }
            }
        })
    }

    private fun initData(){
        viewModel.fetchMovieDetails(getString(R.string.api_key),497582)
    }

    private fun retrySnackBar(){
        contentView?.let {
            Snackbar.make(it,R.string.texto_erro_internet_snack, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.texto_tente_novamente) {
                    initData()
                }.show()
        }
    }

    private fun setVisibilities(imgHeart: Boolean = false,txtLike: Boolean = false,imgStar: Boolean = false, txtPopularity: Boolean = false,
                                chkLike: Boolean = false){
        imageHeart.visible(imgHeart)
        textLikes.visible(txtLike)
        imageStar.visible(imgStar)
        textPopularity.visible(txtPopularity)
        likeIcon.visible(chkLike)
    }

}