package com.backbase.assignment.ui.utils

import com.backbase.assignment.ui.data.remote.movielist.PlayingNowResponse

object ValidationUtil {

    fun validateMovie(playingNowResponse: PlayingNowResponse) : Boolean {
        if (playingNowResponse.results.isEmpty()) {
            return true
        }
        return false
    }
}