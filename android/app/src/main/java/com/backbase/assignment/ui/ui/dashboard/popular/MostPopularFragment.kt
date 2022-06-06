package com.backbase.assignment.ui.ui.dashboard.popular

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.backbase.assignment.BR
import com.backbase.assignment.R
import com.backbase.assignment.databinding.FragmentMostPopularBinding
import com.backbase.assignment.databinding.PopularItemBinding
import com.backbase.assignment.ui.adapter.BaseRecyclerViewAdapter
import com.backbase.assignment.ui.adapter.PagerMarginItemDecoration
import com.backbase.assignment.ui.data.remote.movielist.Result
import com.backbase.assignment.ui.network.model.Status
import com.backbase.assignment.ui.ui.base.BaseFragment
import com.backbase.assignment.ui.ui.base.BaseNavigator
import com.backbase.assignment.ui.ui.dashboard.DashboardViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.KoinComponent


class MostPopularFragment : BaseFragment<FragmentMostPopularBinding, DashboardViewModel>(),KoinComponent,
    BaseNavigator {
    private val movieBoxViewModel by sharedViewModel<DashboardViewModel>()
    private lateinit var mAdapter: BaseRecyclerViewAdapter<Result, PopularItemBinding>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieBoxViewModel.setNavigator(this)
        setPlayingNowAdapter()
        movieBoxViewModel.getMostPopularResponse()
        movieBoxViewModel.mostPopularResponse.observe(viewLifecycleOwner
        ) {
            it?.let { it1 ->
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
            }
        }
    }
    private fun setPlayingNowAdapter() {
        mAdapter = BaseRecyclerViewAdapter(
            R.layout.popular_item,
            BR.popularItem, ArrayList(),null)
        viewDataBinding?.viewpager?.let {
            it.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            it.offscreenPageLimit = 3
            it.clipToPadding=false
            it.clipChildren=false
            it.getChildAt(0).overScrollMode=RecyclerView.OVER_SCROLL_NEVER
            val nextItemVisibleWidth = resources.getDimension(R.dimen.next_item_visible_width)
            val currentItemMargin =
                resources.getDimension(R.dimen.viewpager_horizontal_margin)
            val pageTranslation = nextItemVisibleWidth + currentItemMargin
            it.setPageTransformer { page: View, position: Float ->
                page.translationX = -pageTranslation * position
                page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
                page.alpha = 0.25f + (1 - kotlin.math.abs(position))
            }
            val itemDecoration = PagerMarginItemDecoration(
                requireContext(),
                R.dimen.viewpager_horizontal_margin
            )
            it.addItemDecoration(itemDecoration)
            it.adapter=mAdapter
            it.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    viewModel.selectedPosition.set(mAdapter.getItems()[position].id)
                    viewModel.title.set(mAdapter.getItems()[position].title)
                    viewModel.overview.set(mAdapter.getItems()[position].overview)
                    viewModel.voteAverage.set(mAdapter.getItems()[position].vote_average)
                }

            })
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
    override val viewModel: DashboardViewModel
        get() = movieBoxViewModel

    override fun onClickView(v: View?) {
       when(v?.id){
           R.id.more_text->{
             findNavController(this).navigate(R.id.action_navigation_most_popular_to_navigation_movie_detail)
           }
       }
    }

    override fun goTo(clazz: Class<*>, mExtras: Bundle?) {
        TODO("Not yet implemented")
    }

}