package com.backbase.assignment.ui.repo.moviedetail

import com.backbase.assignment.ui.data.remote.moviedetail.MovieDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IMovieDetail {
    @GET("3/movie/{MOVIE_ID}?language=en-US")
     fun getMovieDetailResponse(@Path("MOVIE_ID")movieID:Int, @Query("api_key")key: String ):Call<MovieDetailResponse>
}