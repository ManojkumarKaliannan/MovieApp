package com.backbase.assignment.ui.repo.popular

import com.backbase.assignment.ui.data.remote.movielist.PlayingNowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IMostPopular {
    @GET("3/movie/popular?language=en-US")
     fun getMostPopularList(@Query("page")page:Int,@Query("api_key")key: String ):Call<PlayingNowResponse>
}