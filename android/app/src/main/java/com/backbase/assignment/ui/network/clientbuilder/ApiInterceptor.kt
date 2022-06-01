package com.backbase.assignment.ui.network.clientbuilder
import com.backbase.assignment.ui.utils.Singleton
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.KoinComponent
import java.io.IOException

class ApiInterceptor : KoinComponent,
    Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .header(Singleton.ACCEPT_KEY, Singleton.APPLICATION_JSON)
            .build()
        return chain.proceed(newRequest)
    }
}