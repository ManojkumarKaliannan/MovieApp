package com.backbase.assignment.ui.repo

import com.backbase.assignment.ui.network.clientbuilder.MovieBoxClientBuilder
import com.backbase.assignment.ui.repo.moviedetail.IMovieDetail
import com.backbase.assignment.ui.repo.playingnow.IPlayingNow
import com.backbase.assignment.ui.repo.popular.IMostPopular

class MovieBoxRepo(private val movieBoxClientBuilder: MovieBoxClientBuilder) {

    fun getPlayingNowRepo():IPlayingNow{
        return movieBoxClientBuilder.getRetrofit().create(IPlayingNow::class.java)
    }

    fun getMostPopularRepo():IMostPopular{
        return movieBoxClientBuilder.getRetrofit().create(IMostPopular::class.java)
    }

    fun getMovieDetailRepo():IMovieDetail{
        return movieBoxClientBuilder.getRetrofit().create(IMovieDetail::class.java)
    }
}