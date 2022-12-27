package com.lucasginard.pelispedia.home.step1ListSeries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lucasginard.pelispedia.BuildConfig
import com.lucasginard.pelispedia.home.HomeActivity
import com.lucasginard.pelispedia.home.step1ListSeries.model.Serie
import com.lucasginard.pelispedia.home.step1ListSeries.presenter.HomeListContract
import com.lucasginard.pelispedia.home.step1ListSeries.presenter.HomeListPresenter
import com.lucasginard.pelispedia.home.step1ListSeries.ui.theme.TemplateExampleTheme
import com.lucasginard.pelispedia.utils.Constants
import com.lucasginard.pelispedia.utils.ExpandableText
import com.lucasginard.pelispedia.utils.contentView

class HomeListFragment : Fragment(), HomeListContract.View {

    lateinit var presenter:HomeListPresenter
    lateinit var flagListSerie: MutableState<Boolean>
    lateinit var listSeries: SnapshotStateList<Serie>
    lateinit var titleHome: MutableState<String>
    lateinit var activityHome:HomeActivity
    val options = listOf(Constants.TITLE_POPULAR,Constants.TITLE_TOP_SCORE,Constants.TITLE_ON_AIR)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = contentView(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)) {
        TemplateExampleTheme() {
            Surface(color = MaterialTheme.colors.background) {
                presenter = HomeListPresenter(this)
                activityHome = activity as HomeActivity
                presenter.getPopularSeries()
                baseHomeList()
            }
        }

    }

    @Composable
    private fun baseHomeList() {
        titleHome = remember { mutableStateOf(options[0]) }
        flagListSerie = remember { mutableStateOf(false) }
        listSeries = remember { mutableStateListOf<Serie>() }
            Column(
                modifier = Modifier
                    .padding(end = 20.dp, start = 20.dp)
                    .fillMaxSize()
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ){
                    Text(
                        text = titleHome.value,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(top = 20.dp, bottom = 20.dp)

                    )
                    componentSpinnerFilter()
                }
                componentListidSeries()
            }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun componentSpinnerFilter(){
        var expanded by remember { mutableStateOf(false) }
        var selectFilter by remember { mutableStateOf(0) }

        Box(
            contentAlignment = Alignment.Center,
        ) {

            IconButton(onClick = {
                expanded = true
            }) {
                Icon(
                    imageVector = Icons.Default.FilterAlt,
                    contentDescription = "Open Options",
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                options.forEachIndexed { itemIndex, itemValue ->
                    DropdownMenuItem(
                        onClick = {
                            titleHome.value = itemValue
                            selectFilter = itemIndex
                            callService(itemValue)
                            expanded = false
                        },
                        enabled = (itemIndex != selectFilter)
                    ) {
                        Text(text = itemValue)
                    }
                }
            }
        }
    }

    private fun callService(call:String){
        when(call){
            Constants.TITLE_POPULAR -> presenter.getPopularSeries()
            Constants.TITLE_ON_AIR-> presenter.getNowPlayingSeries()
            Constants.TITLE_TOP_SCORE-> presenter.getTopRatedSeries()
        }
    }

    @Composable
    private fun componentListidSeries() {
        if (flagListSerie.value) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ) {
                items(listSeries.size) {
                    val item = listSeries[it]
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .width(200.dp)
                            .padding(bottom = 16.dp)
                            .clickable(
                                onClick = {
                                    goDetail(item)
                                }
                            )
                    ) {
                        AsyncImage(
                            model = "${BuildConfig.BASE_URL_IMAGE}${item.posterPath}",
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(180.dp)
                                .fillMaxWidth()
                        )

                        item.title?.let { it1 ->
                            componentIconWithText(Icons.Default.Movie,
                                it1
                            )
                        }

                        item.voteAverage?.toString().let { it1 ->
                            componentIconWithText(Icons.Default.StarRate,
                                it1 ?: ""
                            )
                        }

                        item.releaseDate?.let { it1 ->
                            componentIconWithText(Icons.Default.CalendarMonth,
                                it1
                            )
                        }

                        item.overview?.let { it1 -> componentDescriptionSerie(it1) }
                    }
                }
            }
        }else{
            CircularProgressIndicator()
        }
    }

    @Composable
    private fun componentIconWithText(icon:ImageVector,textInput:String){
        Row {
            Icon(
                imageVector = icon,
                contentDescription = "icon reference",
            )
            Text(
                text = textInput,
                maxLines = 1
            )
        }
    }

    @Composable
    private fun componentDescriptionSerie(description:String) {
        if(description.isNotEmpty()) ExpandableText(text = description)
    }

    override fun showSeries(isSucess:Boolean, title:String) {
        flagListSerie.value = isSucess
        titleHome.value = title
        listSeries.clear()
        listSeries.addAll(presenter.getListSeries)
    }

    override fun goDetail(serie: Serie) {
        activityHome.goFragmentDetail(serie)
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreviewAbout() {
        TemplateExampleTheme {
            baseHomeList()
        }
    }



}