package com.lucasginard.pelispedia.home.step2DetailMovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import coil.compose.AsyncImage
import com.lucasginard.pelispedia.BuildConfig
import com.lucasginard.pelispedia.home.step1ListMovies.model.Movie
import com.lucasginard.pelispedia.home.step1ListMovies.ui.theme.TemplateExampleTheme
import com.lucasginard.pelispedia.home.step2DetailMovie.presenter.DetailMovieContract
import com.lucasginard.pelispedia.home.step2DetailMovie.presenter.DetailMoviePresenter
import com.lucasginard.pelispedia.utils.contentView

class DetailMovieFragment(var movie: Movie): Fragment(),DetailMovieContract.View {

    lateinit var presenter:DetailMoviePresenter
    lateinit var flagDetailMovie: MutableState<Boolean>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = contentView(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)) {
        TemplateExampleTheme() {
            Surface(color = MaterialTheme.colors.background) {
                presenter = DetailMoviePresenter(this)
                movie.id?.let { presenter.getCreditsMovie(it) }
                baseHomeDetail()
            }
        }
    }

    @Composable
    private fun baseHomeDetail() {
        flagDetailMovie = remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .padding(end = 20.dp, start = 20.dp)
                .fillMaxSize()
                .fillMaxWidth()
        ) {
            if (flagDetailMovie.value){
                AsyncImage(
                    model = "${BuildConfig.BASE_URL_IMAGE}${movie.posterPath}",
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(400.dp)
                        .fillMaxWidth()
                )
                Text(text = presenter.getDirector())
            }
        }
    }

    override fun loadDetailMovie(isSucess: Boolean) {
        flagDetailMovie.value = isSucess
    }

    override fun errorUI() {
        TODO("Not yet implemented")
    }

}