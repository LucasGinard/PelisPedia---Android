package com.lucasginard.pelispedia.utils

import com.lucasginard.pelispedia.home.step1ListSeries.model.Serie
import com.lucasginard.pelispedia.home.step2DetailSerie.model.CreditsSerieResponse
import com.lucasginard.pelispedia.home.step2DetailSerie.model.DetailSerieResponse

class SessionCache {
    companion object{
        var filterSeries = 0
        var listSeriesCache = ArrayList<Serie>()
        var listElencoCache = ArrayList<CreditsSerieResponse>()
        var detailSerieCache = ArrayList<DetailSerieResponse>()
        fun clear(){
            filterSeries = 0
        }
    }
}