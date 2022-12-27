package com.lucasginard.pelispedia.home.step2DetailSerie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import coil.compose.AsyncImage
import com.lucasginard.pelispedia.BuildConfig
import com.lucasginard.pelispedia.home.step1ListSeries.model.Serie
import com.lucasginard.pelispedia.home.step1ListSeries.ui.theme.TemplateExampleTheme
import com.lucasginard.pelispedia.home.step2DetailSerie.presenter.DetailidSerieContract
import com.lucasginard.pelispedia.home.step2DetailSerie.presenter.DetailSeriePresenter
import com.lucasginard.pelispedia.utils.contentView
import com.lucasginard.pelispedia.R
import com.lucasginard.pelispedia.utils.ExpandableText

class DetailSerieFragment(var serie: Serie): Fragment(),DetailidSerieContract.View {

    lateinit var presenter:DetailSeriePresenter
    lateinit var flagDetailSerie: MutableState<Boolean>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = contentView(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)) {
        TemplateExampleTheme() {
            Surface(color = MaterialTheme.colors.background) {
                presenter = DetailSeriePresenter(this)
                serie.id?.let { presenter.getCreditsSerie(it) }
                baseHomeDetail()
            }
        }
    }

    @Composable
    private fun baseHomeDetail() {
        flagDetailSerie = remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .padding(end = 20.dp, start = 20.dp)
                .fillMaxSize()
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (flagDetailSerie.value){
                AsyncImage(
                    model = "${BuildConfig.BASE_URL_IMAGE}${serie.posterPath}",
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(400.dp)
                        .fillMaxWidth()
                )
                componentDirector()
                componentRatedSerie()
                componentDescriptionSerie()
                componentCastList()
            }else{
                CircularProgressIndicator()
            }
        }
    }

    @Composable
    private fun componentDescriptionSerie() {
        Row(
            Modifier.padding(top = 12.dp, bottom = 12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.titleDescription),
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(end = 8.dp)
            )
            serie.overview?.let { ExpandableText(text = it) }
        }
    }

    @Composable
    private fun componentRatedSerie(){
        Row {
            Icon(
                imageVector = Icons.Default.StarRate,
                contentDescription = "icon reference",
            )
            Text(
                text = stringResource(id = R.string.titleRated),
                modifier = Modifier
                    .padding(end = 8.dp)
            )
            Text(
                text = serie.voteAverage.toString(),
            )
        }
    }

    @Composable
    private fun componentDirector() {
        Row(
            Modifier.padding(top = 12.dp, bottom = 12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.titleDirector),
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(end = 8.dp)
            )
            Text(
                text = presenter.getDirector(),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun componentCastList() {
        Text(
            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
            text = stringResource(id = R.string.titleCast),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
        )
        LazyRow {
            itemsIndexed(presenter.getListCast) { index, item ->
                Card(
                    onClick = {

                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .width(120.dp)
                        .height(220.dp),
                    elevation = 6.dp
                ) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(5.dp))
                        AsyncImage(
                            model = "${BuildConfig.BASE_URL_IMAGE}${item.profilePath}",
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(120.dp)
                                .padding(5.dp),
                            alignment = Alignment.Center,
                            placeholder = painterResource(id = R.drawable.ic_launcher_foreground)

                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        item.name?.let {
                            Text(
                                text = it,
                                modifier = Modifier.padding(4.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }

    override fun loadDetailSerie(isSucess: Boolean) {
        flagDetailSerie.value = isSucess
    }

    override fun errorUI() {
        TODO("Not yet implemented")
    }

}