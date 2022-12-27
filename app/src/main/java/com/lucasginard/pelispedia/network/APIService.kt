package com.lucasginard.pelispedia.network

import com.lucasginard.pelispedia.BuildConfig
import com.lucasginard.pelispedia.home.step1ListMovies.model.MoviesResponse
import com.lucasginard.pelispedia.home.step2DetailMovie.model.CreditsMovieResponse
import com.lucasginard.pelispedia.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("language") language:String = Constants.LANGUAGE_DEFAULT,
                                 @Query("api_key") apiKey:String = BuildConfig.API_KEY): Response<MoviesResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("language") language:String = Constants.LANGUAGE_DEFAULT,
                                  @Query("api_key") apiKey:String = BuildConfig.API_KEY): Response<MoviesResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("language") language:String = Constants.LANGUAGE_DEFAULT,
                                    @Query("api_key") apiKey:String = BuildConfig.API_KEY): Response<MoviesResponse>

    @GET("movie/{id}/credits")
    suspend fun getCreditsMovie(@Path("id") id:Int,
                                @Query("language") language:String = Constants.LANGUAGE_DEFAULT,
                                @Query("api_key") apiKey:String = BuildConfig.API_KEY
    ): Response<CreditsMovieResponse>



}