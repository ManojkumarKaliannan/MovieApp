package com.backbase.assignment.ui.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.backbase.assignment.BR
import com.backbase.assignment.R
import com.backbase.assignment.databinding.ActivityDashBoardBinding
import com.backbase.assignment.ui.ui.base.BaseActivity
import com.backbase.assignment.ui.utils.NetworkConnectionStatus
import com.backbase.assignment.ui.utils.Singleton.isNetworkAvailable
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.core.KoinComponent
import org.koin.core.inject


class DashBoardActivity : BaseActivity<ActivityDashBoardBinding, DashboardViewModel>(),KoinComponent{
    private  val movieBoxViewModel: DashboardViewModel by inject()
    private  val networkConnection: NetworkConnectionStatus by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navView = getViewDataBinding().navView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_dash_board) as NavHostFragment
        val navController = navHostFragment.navController
        navView.setupWithNavController(navController)
        //Hiding the bottom navigation bar while navigating to movie detail screen
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_movie_detail -> hideBottomNav(navView)
                else -> showBottomNav(navView)
            }
        }
        observeResponse()
    }

    private fun observeResponse() {
        //observing internet connection status
        networkConnection.observe(this) { isConnected ->
            isNetworkAvailable = isConnected
        }
    }

    private fun hideBottomNav(navView: BottomNavigationView) {
        navView.visibility= View.GONE
    }
    private fun showBottomNav(navView: BottomNavigationView) {
        navView.visibility= View.VISIBLE
    }

    override fun getBindingVariable(): Int {
       return BR.movieBoxViewModel
    }

    override fun getLayoutId(): Int {
       return R.layout.activity_dash_board
    }

    override fun getViewModel(): DashboardViewModel {
      return movieBoxViewModel
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_dash_board)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}
