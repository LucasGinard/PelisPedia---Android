package com.lucasginard.pelispedia.home.step1ListSeries.presenter

import android.util.Log
import com.lucasginard.pelispedia.home.step1ListSeries.model.Serie
import com.lucasginard.pelispedia.network.APIService
import com.lucasginard.pelispedia.network.Service
import com.lucasginard.pelispedia.utils.Constants
import com.lucasginard.pelispedia.utils.SessionCache
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeListPresenter(var view:HomeListContract.View) :HomeListContract.Presenter{

    val getListSeries = ArrayList<Serie>()
    val exceptionHandler = CoroutineExceptionHandler{_ , throwable->
        view.showError(true)
    }

    override fun getPopularSeries() {
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val call = Service.getService().create(APIService::class.java).getPopularSeries()
            if(call.isSuccessful){
                getListSeries.clear()
                call.body()?.results?.let {
                    getListSeries.addAll(it)
                    SessionCache.listSeriesCache = it
                }
                view.showSeries(true, Constants.TITLE_POPULAR)
            }
        }
    }

    override fun getTopRatedSeries() {
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val call = Service.getService().create(APIService::class.java).getTopRatedSeries()
            if(call.isSuccessful){
                getListSeries.clear()
                call.body()?.results?.let {
                    getListSeries.addAll(it)
                    SessionCache.listSeriesCache = it
                }
                view.showSeries(true, Constants.TITLE_TOP_SCORE)
            }
        }
    }

    override fun getNowPlayingSeries() {
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val call = Service.getService().create(APIService::class.java).getNowPlayingSeries()
            if(call.isSuccessful){
                getListSeries.clear()
                call.body()?.results?.let {
                    getListSeries.addAll(it)
                    SessionCache.listSeriesCache = it
                }
                view.showSeries(true, Constants.TITLE_ON_AIR)
            }
        }
    }

    fun callServiceForSection(section:Int){
        when(section){
            Constants.TITLE_POPULAR_ID -> getPopularSeries()
            Constants.TITLE_ON_AIR_ID -> getNowPlayingSeries()
            Constants.TITLE_TOP_SCORE_ID -> getTopRatedSeries()
        }
    }
}

interface HomeListContract{
    interface View{
        fun showSeries(isSucess:Boolean, title:String)
        fun showError(isError:Boolean)
        fun goDetail(serie: Serie)
    }

    interface Presenter{
        fun getPopularSeries()
        fun getTopRatedSeries()
        fun getNowPlayingSeries()
    }
}