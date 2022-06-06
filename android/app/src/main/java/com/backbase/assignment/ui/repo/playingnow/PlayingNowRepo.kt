package com.backbase.assignment.ui.repo.playingnow

import androidx.lifecycle.MutableLiveData
import com.backbase.assignment.ui.data.remote.movielist.PlayingNowResponse
import com.backbase.assignment.ui.network.clientbuilder.BaseRepository
import com.backbase.assignment.ui.network.model.Resource
import com.backbase.assignment.ui.repo.MovieBoxRepo

class PlayingNowRepo(private val movieBoxRepo: MovieBoxRepo): BaseRepository() {

    fun getPlayingNow(page: Int, key: String,
             playingNowResponse: MutableLiveData<Resource<PlayingNowResponse>>) {
          movieBoxRepo.getPlayingNowRepo().
          getPlayingNow(page = page, key = key).enqueue(getCallback(playingNowResponse))
    }

}