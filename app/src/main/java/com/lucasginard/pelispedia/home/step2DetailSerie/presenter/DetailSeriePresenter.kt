package com.lucasginard.pelispedia.home.step2DetailSerie.presenter

import android.content.Context
import android.widget.Toast
import com.lucasginard.pelispedia.home.step2DetailSerie.model.Cast
import com.lucasginard.pelispedia.home.step2DetailSerie.model.Crew
import com.lucasginard.pelispedia.home.step2DetailSerie.model.DetailSerieResponse
import com.lucasginard.pelispedia.network.APIService
import com.lucasginard.pelispedia.network.Service
import com.lucasginard.pelispedia.utils.Constants
import com.lucasginard.pelispedia.utils.SessionCache
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailSeriePresenter(var view:DetailidSerieContract.View):DetailidSerieContract.Presenter {

    var getListCast = ArrayList<Cast>()
    var getListCrew = ArrayList<Crew>()
    var getDetailSerie = DetailSerieResponse()
    val exceptionHandler = CoroutineExceptionHandler{_ , throwable->
        view.errorUI()
    }

    override fun getDetailsSerie(idSerie: Int) {
        getCreditsSerie(idSerie)
        getDetailSerie(idSerie)
    }

    override fun getCreditsSerie(idSerie:Int) {
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val call = Service.getService().create(APIService::class.java).getCreditsSerie(id = idSerie)
            if(call.isSuccessful){
                getListCast.clear()
                getListCrew.clear()
                if (!SessionCache.listElencoCache.contains(call.body())){
                    call.body()?.let { SessionCache.listElencoCache.add(it) }
                }
                call.body()?.crew?.let {
                    getListCrew.addAll(it)
                }
                call.body()?.cast?.let {
                    getListCast.addAll(it)
                }
                view.loadCreditsSerie(true)
            }else{
                Toast.makeText(view.getContextFragment(), Constants.ERRRO_DEFAULT, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun getDetailSerie(idSerie: Int) {
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val call = Service.getService().create(APIService::class.java).getDetailSerie(id = idSerie)
            if(call.isSuccessful){
                call.body()?.let {
                    if (!SessionCache.detailSerieCache.contains(call.body())){
                        call.body()?.let { SessionCache.detailSerieCache.add(it) }
                    }
                    getDetailSerie = it
                }
                view.loadDetailSerie(true)
            }else{
                Toast.makeText(view.getContextFragment(), Constants.ERRRO_DEFAULT, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun getDirector(): String {
        return getListCrew.find { it.knownForDepartment == Constants.DIRECTOR_SERIE }?.name ?: ""
    }


}

interface DetailidSerieContract{
    interface View{
        fun loadCreditsSerie(isSucess:Boolean)
        fun loadDetailSerie(isSucess:Boolean)
        fun errorUI()
        fun getContextFragment():Context
    }

    interface Presenter{
        fun getCreditsSerie(idSerie:Int)
        fun getDetailSerie(idSerie: Int)
        fun getDirector():String
        fun getDetailsSerie(idSerie: Int)
    }
}