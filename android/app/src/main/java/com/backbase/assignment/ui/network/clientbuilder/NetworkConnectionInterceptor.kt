package com.backbase.assignment.ui.network.clientbuilder

import com.backbase.assignment.ui.utils.Singleton.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.koin.core.KoinComponent
import java.io.IOException

class NetworkConnectionInterceptor : Interceptor,KoinComponent {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable) {
            // Throwing our custom exception 'NoConnectivityException'
            throw NoConnectivityException()
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}

class NoConnectivityException : IOException() {
    // You can send any message whatever you want from here.
    override val message: String
        get() = "No Internet Connection"
    // You can send any message whatever you want from here.
}