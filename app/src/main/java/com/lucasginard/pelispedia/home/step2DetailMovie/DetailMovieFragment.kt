package com.lucasginard.pelispedia.home.step2DetailMovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
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
import com.lucasginard.pelispedia.utils.contentView

class DetailMovieFragment(var movie: Movie): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = contentView(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)) {
        TemplateExampleTheme() {
            Surface(color = MaterialTheme.colors.background) {
                baseHomeDetail()
            }
        }
    }

    @Composable
    private fun baseHomeDetail() {
        Column(
            modifier = Modifier
                .padding(end = 20.dp, start = 20.dp)
                .fillMaxSize()
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = "${BuildConfig.BASE_URL_IMAGE}${movie.posterPath}",
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(180.dp)
                    .fillMaxWidth()
            )
        }
    }

}