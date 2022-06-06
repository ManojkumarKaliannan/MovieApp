package com.backbase.assignment.ui.repo.moviedetail

import androidx.lifecycle.MutableLiveData
import com.backbase.assignment.ui.data.remote.moviedetail.MovieDetailResponse
import com.backbase.assignment.ui.data.remote.movielist.PlayingNowResponse
import com.backbase.assignment.ui.network.clientbuilder.BaseRepository
import com.backbase.assignment.ui.network.model.Resource
import com.backbase.assignment.ui.repo.MovieBoxRepo

class MovieDetailRepo(private val movieBoxRepo: MovieBoxRepo): BaseRepository() {

    fun getMovieDetailRepo(movieId: Int, key: String,
             movieDetailResponse: MutableLiveData<Resource<MovieDetailResponse>>) {
          movieBoxRepo.getMovieDetailRepo().
          getMovieDetailResponse(movieID = movieId, key = key).enqueue(getCallback(movieDetailResponse))
    }

}