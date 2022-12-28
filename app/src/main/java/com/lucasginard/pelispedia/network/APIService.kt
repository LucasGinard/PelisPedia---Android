package com.lucasginard.pelispedia.network

import com.lucasginard.pelispedia.BuildConfig
import com.lucasginard.pelispedia.home.step1ListSeries.model.SerieResponse
import com.lucasginard.pelispedia.home.step2DetailSerie.model.CreditsSerieResponse
import com.lucasginard.pelispedia.home.step2DetailSerie.model.DetailSerieResponse
import com.lucasginard.pelispedia.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("tv/popular")
    suspend fun getPopularSeries(@Query("language") language:String = Constants.LANGUAGE_DEFAULT,
                                 @Query("api_key") apiKey:String = BuildConfig.API_KEY): Response<SerieResponse>

    @GET("tv/top_rated")
    suspend fun getTopRatedSeries(@Query("language") language:String = Constants.LANGUAGE_DEFAULT,
                                  @Query("api_key") apiKey:String = BuildConfig.API_KEY): Response<SerieResponse>

    @GET("tv/on_the_air")
    suspend fun getNowPlayingSeries(@Query("language") language:String = Constants.LANGUAGE_DEFAULT,
                                    @Query("api_key") apiKey:String = BuildConfig.API_KEY): Response<SerieResponse>

    @GET("tv/{id}/credits")
    suspend fun getCreditsSerie(@Path("id") id:Int,
                                @Query("language") language:String = Constants.LANGUAGE_DEFAULT,
                                @Query("api_key") apiKey:String = BuildConfig.API_KEY
    ): Response<CreditsSerieResponse>

    @GET("tv/{id}")
    suspend fun getDetailSerie(@Path("id") id:Int,
                                @Query("language") language:String = Constants.LANGUAGE_DEFAULT,
                                @Query("api_key") apiKey:String = BuildConfig.API_KEY
    ): Response<DetailSerieResponse>



}