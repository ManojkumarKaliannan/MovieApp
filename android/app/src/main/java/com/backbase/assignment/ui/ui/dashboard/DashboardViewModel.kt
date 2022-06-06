package com.backbase.assignment.ui.ui.dashboard

import android.app.Application
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.backbase.assignment.ui.data.remote.moviedetail.MovieDetailResponse
import com.backbase.assignment.ui.data.remote.movielist.PlayingNowResponse
import com.backbase.assignment.ui.network.model.Resource
import com.backbase.assignment.ui.repo.moviedetail.MovieDetailRepo
import com.backbase.assignment.ui.repo.playingnow.PlayingNowRepo
import com.backbase.assignment.ui.repo.popular.MostPopularRepo
import com.backbase.assignment.ui.ui.base.BaseNavigator
import com.backbase.assignment.ui.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class DashboardViewModel(application: Application) :BaseViewModel<BaseNavigator>(application),KoinComponent{
    var networkStatus=ObservableBoolean(true)
    var playingNowResponse = MutableLiveData<Resource<PlayingNowResponse>>()
    var mostPopularResponse = MutableLiveData<Resource<PlayingNowResponse>>()
    var movieDetailResponse = MutableLiveData<Resource<MovieDetailResponse>>()
    private val movieBoxRepo: PlayingNowRepo by inject()
    private val mostPopularRepo: MostPopularRepo by inject()
    private val movieDetailRepo: MovieDetailRepo by inject()
    var title=ObservableField("")
    var selectedPosition=ObservableField(0)
    var overview=ObservableField("")
    var voteAverage=ObservableField(0.0)
    var posterPath=ObservableField("")
    var movieTime=ObservableField("")
    var movieDate=ObservableField("")
    var movieID=ObservableField("")

   fun getPlayingNowResponse(){
       viewModelScope.launch {
           movieBoxRepo.getPlayingNow(1,"55957fcf3ba81b137f8fc01ac5a31fb5",playingNowResponse)
       }

    }
    fun getMostPopularResponse(){
        viewModelScope.launch {
            mostPopularRepo.getMostPopularResponse(1,"55957fcf3ba81b137f8fc01ac5a31fb5",mostPopularResponse)
        }

    }
    fun getMostDetailResponse(selectedPosition: Int) {
        viewModelScope.launch {
            movieDetailRepo.getMovieDetailRepo(selectedPosition,"55957fcf3ba81b137f8fc01ac5a31fb5",movieDetailResponse)
        }
    }
    //Handling click events
    fun onClickAction(view: View?) {
        getNavigator().onClickView(view)
    }

}