package com.backbase.assignment.ui.ui.dashboard.popular

import android.os.Bundle
import android.view.View
import com.backbase.assignment.BR
import com.backbase.assignment.R
import com.backbase.assignment.databinding.FragmentMostPopularBinding
import com.backbase.assignment.ui.ui.base.BaseFragment
import com.backbase.assignment.ui.ui.dashboard.MovieBoxViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.KoinComponent

class MostPopularFragment : BaseFragment<FragmentMostPopularBinding,MovieBoxViewModel>(),KoinComponent {
    private val movieBoxViewModel by sharedViewModel<MovieBoxViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieBoxViewModel.textPopular.observe(viewLifecycleOwner) {
            viewDataBinding?.textDashboard?.text = it
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewDataBinding = null
    }

    override val bindingVariable: Int
        get() = BR.movieBoxViewModel
    override val layoutId: Int
        get() = R.layout.fragment_most_popular
    override val viewModel: MovieBoxViewModel
        get() = movieBoxViewModel
}