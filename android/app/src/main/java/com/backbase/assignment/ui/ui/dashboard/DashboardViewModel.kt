package com.backbase.assignment.ui.ui.dashboard

import android.app.Application
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.backbase.assignment.ui.data.remote.moviedetail.MovieDetailResponse
import com.backbase.assignment.ui.data.remote.movielist.PlayingNowResponse
import com.backbase.assignment.ui.network.model.Resource
import com.backbase.assignment.ui.repo.moviedetail.MovieDetailRepo
import com.backbase.assignment.ui.repo.playingnow.PlayingNowRepo
import com.backbase.assignment.ui.repo.popular.MostPopularRepo
import com.backbase.assignment.ui.ui.base.BaseNavigator
import com.backbase.assignment.ui.ui.base.BaseViewModel
import com.backbase.assignment.ui.utils.Singleton.APIKEY
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject


class DashboardViewModel(application: Application) :BaseViewModel<BaseNavigator>(application),KoinComponent{
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
    var progressBarVisibility=ObservableField(true)
    private var mState: SavedStateHandle? = null

    fun SavedStateViewModel(savedStateHandle: SavedStateHandle?) {
        mState = savedStateHandle
    }
    //getting playinglist response
   fun getPlayingNowResponse(mPageCount: Int) {
       viewModelScope.launch {
           progressBarVisibility.set(true)
            movieBoxRepo.getPlayingNow(mPageCount,APIKEY,playingNowResponse)
       }
    }
    //getting popular movie list response
    fun getMostPopularResponse(mPageCount: Int){
        viewModelScope.launch {
            progressBarVisibility.set(true)
            mostPopularRepo.getMostPopularResponse(mPageCount,APIKEY,mostPopularResponse)
        }

    }
    //getting movie detail response
    fun getMovieDetailResponse(selectedPosition: Int) {
        viewModelScope.launch {
            progressBarVisibility.set(true)
            movieDetailRepo.getMovieDetailRepo(selectedPosition,APIKEY,movieDetailResponse)
        }
    }
    //Handling click events
    fun onClickAction(view: View?) {
        getNavigator().onClickView(view)
    }

}