package com.lucasginard.pelispedia.home.step2DetailSerie.presenter

import com.lucasginard.pelispedia.home.step2DetailSerie.model.Cast
import com.lucasginard.pelispedia.home.step2DetailSerie.model.Crew
import com.lucasginard.pelispedia.network.APIService
import com.lucasginard.pelispedia.network.Service
import com.lucasginard.pelispedia.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailSeriePresenter(var view:DetailidSerieContract.View):DetailidSerieContract.Presenter {

    var getListCast = ArrayList<Cast>()
    var getListCrew = ArrayList<Crew>()

    override fun getCreditsSerie(idSerie:Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val call = Service.getService().create(APIService::class.java).getCreditsSerie(id = idSerie)
            if(call.isSuccessful){
                getListCast.clear()
                getListCrew.clear()
                call.body()?.crew?.let { getListCrew.addAll(it) }
                call.body()?.cast?.let { getListCast.addAll(it) }
                view.loadDetailSerie(true)
            }else{

            }
        }
    }

    override fun getDirector(): String {
        return getListCrew.find { it.knownForDepartment == Constants.DIRECTOR_SERIE }?.name ?: ""
    }


}

interface DetailidSerieContract{
    interface View{
        fun loadDetailSerie(isSucess:Boolean)
        fun errorUI()
    }

    interface Presenter{
        fun getCreditsSerie(idSerie:Int)
        fun getDirector():String

    }
}