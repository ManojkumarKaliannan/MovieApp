package com.backbase.assignment.ui.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.backbase.assignment.BR
import com.backbase.assignment.R
import com.backbase.assignment.databinding.ActivityDashBoardBinding
import com.backbase.assignment.ui.ui.base.BaseActivity
import com.backbase.assignment.ui.utils.NetworkConnectionStatus
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
        networkConnection.observe(this) { isConnected ->
            if (!isConnected) {
                putToast(getString(R.string.no_internet))
            }
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_movie_detail -> hideBottomNav(navView)
                else -> showBottomNav(navView)
            }
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

    private fun loadFragment(fragment: Fragment?): Boolean {

        //switching fragment
        if (fragment != null&&fragment.isAdded) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment_activity_dash_board, fragment)
                .addToBackStack(fragment.toString())
                .commit()
            return true
        }
        return false
    }
}
