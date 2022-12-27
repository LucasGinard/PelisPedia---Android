package com.lucasginard.pelispedia.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lucasginard.pelispedia.databinding.ActivityHomeBinding
import com.lucasginard.pelispedia.home.step1ListMovies.HomeListFragment
import com.lucasginard.pelispedia.home.step1ListMovies.model.Movie
import com.lucasginard.pelispedia.home.step2DetailMovie.DetailMovieFragment


class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFragment()
    }

    private fun setupFragment(){
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, HomeListFragment())
            .commit()
    }

    fun goFragmentDetail(movie: Movie){
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, DetailMovieFragment(movie))
            .addToBackStack(null)
            .commit()
    }
}