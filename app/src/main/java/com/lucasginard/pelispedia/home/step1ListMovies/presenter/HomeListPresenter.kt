package com.lucasginard.pelispedia.home.step1ListMovies.presenter

import com.lucasginard.pelispedia.home.step1ListMovies.model.Movie
import com.lucasginard.pelispedia.network.APIService
import com.lucasginard.pelispedia.network.Service
import com.lucasginard.pelispedia.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeListPresenter(var view:HomeListContract.View) :HomeListContract.Presenter{

    val getListMovies = ArrayList<Movie>()

    override fun getPopularMovies() {
        CoroutineScope(Dispatchers.Main).launch {
            val call = Service.getService().create(APIService::class.java).getPopularMovies()
            if(call.isSuccessful){
                getListMovies.clear()
                call.body()?.results?.let { getListMovies.addAll(it) }
                view.showMovies(true, Constants.TITLE_POPULAR)
            }else{

            }
        }
    }

    override fun getTopRatedMovies() {
        CoroutineScope(Dispatchers.Main).launch {
            val call = Service.getService().create(APIService::class.java).getTopRatedMovies()
            if(call.isSuccessful){
                getListMovies.clear()
                call.body()?.results?.let { getListMovies.addAll(it) }
                view.showMovies(true, Constants.TITLE_TOP_SCORE)
            }else{

            }
        }
    }

    override fun getNowPlayingMovies() {
        CoroutineScope(Dispatchers.Main).launch {
            val call = Service.getService().create(APIService::class.java).getNowPlayingMovies()
            if(call.isSuccessful){
                getListMovies.clear()
                call.body()?.results?.let { getListMovies.addAll(it) }
                view.showMovies(true, Constants.TITLE_ON_AIR)
            }else{

            }
        }
    }

}

interface HomeListContract{
    interface View{
        fun showMovies(isSucess:Boolean,title:String)
    }

    interface Presenter{
        fun getPopularMovies()
        fun getTopRatedMovies()
        fun getNowPlayingMovies()
    }
}