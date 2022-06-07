package com.backbase.assignment.ui.di

import com.backbase.assignment.ui.network.clientbuilder.ApiInterceptor
import com.backbase.assignment.ui.network.clientbuilder.MovieBoxClientBuilder
import com.backbase.assignment.ui.network.clientbuilder.NetworkConnectionInterceptor
import com.backbase.assignment.ui.repo.MovieBoxRepo
import com.backbase.assignment.ui.repo.moviedetail.MovieDetailRepo
import com.backbase.assignment.ui.repo.playingnow.PlayingNowRepo
import com.backbase.assignment.ui.repo.popular.MostPopularRepo
import com.backbase.assignment.ui.ui.dashboard.DashboardViewModel
import com.backbase.assignment.ui.utils.NetworkConnectionStatus
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module
import org.koin.experimental.builder.single

class KoinCoreModule {

    val networkModule= module {
        single<ApiInterceptor>()
        single<NetworkConnectionInterceptor>()
        single{MovieBoxClientBuilder(get(),get())}
    }

    val apiModule= module {
        single{PlayingNowRepo(get())}
        single{MostPopularRepo(get())}
        single{MovieBoxRepo(get())}
        single{MovieDetailRepo(get()) }
    }

    val viewModelModule = module {
        viewModel<DashboardViewModel>()
        single<NetworkConnectionStatus>()
    }
}