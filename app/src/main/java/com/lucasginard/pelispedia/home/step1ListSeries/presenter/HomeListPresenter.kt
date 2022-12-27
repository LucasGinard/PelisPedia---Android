package com.lucasginard.pelispedia.home.step1ListSeries.presenter

import com.lucasginard.pelispedia.home.step1ListSeries.model.Serie
import com.lucasginard.pelispedia.network.APIService
import com.lucasginard.pelispedia.network.Service
import com.lucasginard.pelispedia.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeListPresenter(var view:HomeListContract.View) :HomeListContract.Presenter{

    val getListSeries = ArrayList<Serie>()

    override fun getPopularSeries() {
        CoroutineScope(Dispatchers.Main).launch {
            val call = Service.getService().create(APIService::class.java).getPopularSeries()
            if(call.isSuccessful){
                getListSeries.clear()
                call.body()?.results?.let { getListSeries.addAll(it) }
                view.showSeries(true, Constants.TITLE_POPULAR)
            }else{

            }
        }
    }

    override fun getTopRatedSeries() {
        CoroutineScope(Dispatchers.Main).launch {
            val call = Service.getService().create(APIService::class.java).getTopRatedSeries()
            if(call.isSuccessful){
                getListSeries.clear()
                call.body()?.results?.let { getListSeries.addAll(it) }
                view.showSeries(true, Constants.TITLE_TOP_SCORE)
            }else{

            }
        }
    }

    override fun getNowPlayingSeries() {
        CoroutineScope(Dispatchers.Main).launch {
            val call = Service.getService().create(APIService::class.java).getNowPlayingSeries()
            if(call.isSuccessful){
                getListSeries.clear()
                call.body()?.results?.let { getListSeries.addAll(it) }
                view.showSeries(true, Constants.TITLE_ON_AIR)
            }else{

            }
        }
    }

}

interface HomeListContract{
    interface View{
        fun showSeries(isSucess:Boolean, title:String)
        fun goDetail(serie: Serie)
    }

    interface Presenter{
        fun getPopularSeries()
        fun getTopRatedSeries()
        fun getNowPlayingSeries()
    }
}