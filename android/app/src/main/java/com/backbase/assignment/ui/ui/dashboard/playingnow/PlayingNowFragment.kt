package com.backbase.assignment.ui.ui.dashboard.playingnow

import android.os.Bundle
import android.view.View
import com.backbase.assignment.BR
import com.backbase.assignment.R
import com.backbase.assignment.databinding.FragmentPlayingNowBinding
import com.backbase.assignment.ui.ui.base.BaseFragment
import com.backbase.assignment.ui.ui.dashboard.MovieBoxViewModel
import com.backbase.assignment.ui.utils.NetworkConnectionStatus
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class PlayingNowFragment : BaseFragment<FragmentPlayingNowBinding,MovieBoxViewModel>(),KoinComponent {
    private val movieBoxViewModel by sharedViewModel<MovieBoxViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieBoxViewModel.textPlaying.observe(viewLifecycleOwner) {
            viewDataBinding?.textHome?.text = it
        }
       viewDataBinding?.prograssBar?.hide()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewDataBinding = null
    }

    override val bindingVariable: Int
        get() = BR.movieBoxViewModel
    override val layoutId: Int
        get() = R.layout.fragment_playing_now
    override val viewModel: MovieBoxViewModel
        get() = movieBoxViewModel
}