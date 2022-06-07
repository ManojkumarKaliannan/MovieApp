package com.backbase.assignment.ui.ui.dashboard.moviedetail

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.backbase.assignment.BR
import com.backbase.assignment.R
import com.backbase.assignment.databinding.FragmentMovieDetailBinding
import com.backbase.assignment.databinding.GenresItemBinding
import com.backbase.assignment.databinding.LanguageItemBinding
import com.backbase.assignment.ui.adapter.BaseRecyclerViewAdapter
import com.backbase.assignment.ui.data.remote.moviedetail.Genre
import com.backbase.assignment.ui.data.remote.moviedetail.SpokenLanguage
import com.backbase.assignment.ui.network.model.Status
import com.backbase.assignment.ui.ui.base.BaseFragment
import com.backbase.assignment.ui.ui.dashboard.DashboardViewModel
import com.backbase.assignment.ui.utils.convertDateFormat
import com.backbase.assignment.ui.utils.convertHoursFormat
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.KoinComponent


class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, DashboardViewModel>(),
    KoinComponent {
    private val movieBoxViewModel by sharedViewModel<DashboardViewModel>()
    private lateinit var adapterLanguage: BaseRecyclerViewAdapter<SpokenLanguage,LanguageItemBinding>
    private lateinit var adapterGenre: BaseRecyclerViewAdapter<Genre,GenresItemBinding>

    override val bindingVariable: Int
        get() = BR.movieViewModelVariable
    override val layoutId: Int
        get() = R.layout.fragment_movie_detail
    override val viewModel: DashboardViewModel
        get() = movieBoxViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLanguageAdapter()
        setGenreAdapter()
        movieBoxViewModel.getMovieDetailResponse(movieBoxViewModel.selectedPosition.get()!!)
        observeResponse()
    }

    private fun observeResponse() {
        movieBoxViewModel.movieDetailResponse.observe(viewLifecycleOwner
        ) {
            it?.let { it1 ->
                when(it1.status){
                    Status.SUCCESS->{
                        it.data?.let { it2->
                            movieBoxViewModel.progressBarVisibility.set(false)
                            viewModel.let {it3->
                                it3.posterPath.set(it2.poster_path)
                                it3.movieDate.set(convertDateFormat(it2.release_date))
                                it3.movieTime.set(convertHoursFormat(it2.runtime.toString()))
                            }
                            adapterLanguage.addDataSet(it2.spoken_languages)
                            adapterLanguage.notifyItemChanged(0)
                            adapterGenre.addDataSet(it2.genres)
                            adapterGenre.notifyItemChanged(0)
                        }
                    }
                    Status.ERROR->{
                        putToast(it.message)
                    }
                    Status.FAILURE->{
                        putToast(it.message)
                    }
                    else->{
                        putToast(it.message)
                    }
                }
                movieBoxViewModel.movieDetailResponse.value=null
            }
        }
    }


    private fun setLanguageAdapter() {
        adapterLanguage = BaseRecyclerViewAdapter(
            R.layout.language_item,
            BR.languageModel, ArrayList(),null)
        val manager = GridLayoutManager(context,4)
        viewDataBinding?.languageRcv?.layoutManager=manager
        viewDataBinding?.languageRcv?.adapter=adapterLanguage
    }
    private fun setGenreAdapter() {
        adapterGenre = BaseRecyclerViewAdapter(
            R.layout.genres_item,
            BR.genreVariable, ArrayList(),null)
        val manager = GridLayoutManager(context,4)
        viewDataBinding?.genresRcv?.layoutManager=manager
        viewDataBinding?.genresRcv?.adapter=adapterGenre
    }


}