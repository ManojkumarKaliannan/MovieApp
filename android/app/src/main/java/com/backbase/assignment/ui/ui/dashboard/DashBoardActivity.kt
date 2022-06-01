package com.backbase.assignment.ui.ui.dashboard

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.backbase.assignment.BR
import com.backbase.assignment.R
import com.backbase.assignment.databinding.ActivityDashBoardBinding
import com.backbase.assignment.ui.ui.base.BaseActivity
import com.backbase.assignment.ui.utils.NetworkConnectionStatus
import org.koin.core.KoinComponent
import org.koin.core.inject

class DashBoardActivity : BaseActivity<ActivityDashBoardBinding, MovieBoxViewModel>(),KoinComponent{
    private  val movieBoxViewModel: MovieBoxViewModel by inject()
    private  val networkConnection: NetworkConnectionStatus by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navView = getViewDataBinding().navView
        val navController = findNavController(R.id.nav_host_fragment_activity_dash_board)
        navView.setupWithNavController(navController)
        networkConnection.observe(this) { isConnected ->
            if (!isConnected) {
                putToast(getString(R.string.no_internet))
            }
        }
    }

    override fun getBindingVariable(): Int {
       return BR.movieBoxViewModel
    }

    override fun getLayoutId(): Int {
       return R.layout.activity_dash_board
    }

    override fun getViewModel(): MovieBoxViewModel {
      return movieBoxViewModel
    }
}