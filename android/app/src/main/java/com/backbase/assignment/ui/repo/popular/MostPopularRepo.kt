package com.backbase.assignment.ui.repo.popular

import androidx.lifecycle.MutableLiveData
import com.backbase.assignment.ui.data.remote.movielist.PlayingNowResponse
import com.backbase.assignment.ui.network.clientbuilder.BaseRepository
import com.backbase.assignment.ui.network.model.Resource
import com.backbase.assignment.ui.repo.MovieBoxRepo

class MostPopularRepo(private val movieBoxRepo: MovieBoxRepo): BaseRepository() {

    fun getMostPopularResponse(page: Int, key: String,
             playingNowResponse: MutableLiveData<Resource<PlayingNowResponse>>) {
          movieBoxRepo.getMostPopularRepo().
          getMostPopularList(page = page, key = key).enqueue(getCallback(playingNowResponse))
    }

}