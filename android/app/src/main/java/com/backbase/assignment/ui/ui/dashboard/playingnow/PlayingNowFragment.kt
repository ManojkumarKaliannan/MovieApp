package com.backbase.assignment.ui.ui.dashboard.playingnow

import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.*
import com.backbase.assignment.BR
import com.backbase.assignment.R
import com.backbase.assignment.databinding.FragmentPlayingNowBinding
import com.backbase.assignment.databinding.PlayingnowItemBinding
import com.backbase.assignment.ui.adapter.BaseRecyclerViewAdapter
import com.backbase.assignment.ui.data.remote.movielist.Result
import com.backbase.assignment.ui.network.model.Status
import com.backbase.assignment.ui.ui.base.BaseFragment
import com.backbase.assignment.ui.ui.dashboard.DashboardViewModel
import org.koin.core.KoinComponent


class PlayingNowFragment : BaseFragment<FragmentPlayingNowBinding, DashboardViewModel>(),KoinComponent {
    private val movieBoxViewModel : DashboardViewModel by navGraphViewModels(R.id.mobile_navigation)
    private lateinit var mAdapter: BaseRecyclerViewAdapter<Result,PlayingnowItemBinding>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPlayingNowAdapter()
        movieBoxViewModel.getPlayingNowResponse()
        putToast("called")
        movieBoxViewModel.playingNowResponse.observe(viewLifecycleOwner
        ) {
            it?.let { it1 ->
                putToast("called1")
                when(it1.status){
                    Status.SUCCESS->{
                        it.data?.let { it2->
                            mAdapter.cleatDataSet()
                            mAdapter.addDataSet(it2.results)
                            mAdapter.notifyItemChanged(0)
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
                movieBoxViewModel.playingNowResponse.value=null
            }
        }

        viewDataBinding?.progressbar?.hide()
    }


    private fun setPlayingNowAdapter() {
        mAdapter = BaseRecyclerViewAdapter(
            R.layout.playingnow_item,
            BR.playingNowVariable, ArrayList(),null)
        val manager = GridLayoutManager(context,2)
        viewDataBinding?.rcvPlaying?.layoutManager=manager
        viewDataBinding?.rcvPlaying?.adapter=mAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewDataBinding = null
    }

    override val bindingVariable: Int
        get() = BR.movieBoxViewModel
    override val layoutId: Int
        get() = R.layout.fragment_playing_now
    override val viewModel: DashboardViewModel
        get() = movieBoxViewModel
}