package com.lucasginard.pelispedia.home.step2DetailMovie.presenter

import com.lucasginard.pelispedia.home.step2DetailMovie.model.Cast
import com.lucasginard.pelispedia.home.step2DetailMovie.model.Crew
import com.lucasginard.pelispedia.network.APIService
import com.lucasginard.pelispedia.network.Service
import com.lucasginard.pelispedia.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailMoviePresenter(var view:DetailMovieContract.View):DetailMovieContract.Presenter {

    var getListCast = ArrayList<Cast>()
    var getListCrew = ArrayList<Crew>()

    override fun getCreditsMovie(idMovie:Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val call = Service.getService().create(APIService::class.java).getCreditsMovie(id = idMovie)
            if(call.isSuccessful){
                getListCast.clear()
                getListCrew.clear()
                call.body()?.crew?.let { getListCrew.addAll(it) }
                call.body()?.cast?.let { getListCast.addAll(it) }
                view.loadDetailMovie(true)
            }else{

            }
        }
    }

    override fun getDirector(): String {
        return getListCrew.find { it.job == Constants.DIRECTOR_MOVIE }?.name ?: ""
    }


}

interface DetailMovieContract{
    interface View{
        fun loadDetailMovie(isSucess:Boolean)
        fun errorUI()
    }

    interface Presenter{
        fun getCreditsMovie(idMovie:Int)
        fun getDirector():String

    }
}