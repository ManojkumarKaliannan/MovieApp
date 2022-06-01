package com.backbase.assignment.ui.di

import com.backbase.assignment.ui.network.clientbuilder.ApiInterceptor
import com.backbase.assignment.ui.network.clientbuilder.MovieBoxClientBuilder
import com.backbase.assignment.ui.ui.dashboard.MovieBoxViewModel
import com.backbase.assignment.ui.utils.NetworkConnectionStatus
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module
import org.koin.experimental.builder.single
import java.lang.reflect.Array.get

class KoinCoreModule {
    val viewModelModule = module {
        viewModel<MovieBoxViewModel>()
        single<NetworkConnectionStatus>()
    }
    val networkModule= module {
        single<ApiInterceptor>()
        single{MovieBoxClientBuilder(get())}
    }

}