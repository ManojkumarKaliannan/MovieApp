package com.backbase.assignment.ui.repo.playingnow

import com.backbase.assignment.ui.data.remote.movielist.PlayingNowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IPlayingNow {
    @GET("3/movie/now_playing?language=en-US")
     fun getPlayingNow(@Query("page")page:Int,@Query("api_key")key: String ):Call<PlayingNowResponse>
}