package com.backbase.assignment.ui.network.clientbuilder


import com.backbase.assignment.BuildConfig
import com.backbase.assignment.ui.utils.Singleton.CONNECT_TIMEOUT
import com.backbase.assignment.ui.utils.Singleton.READ_TIMEOUT
import com.backbase.assignment.ui.utils.Singleton.WRITE_TIMEOUT
import com.google.gson.GsonBuilder
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.*


class MovieBoxClientBuilder(private val interceptor: ApiInterceptor) : KoinComponent {

    fun getRetrofit()= retrofit

    private val httpLoggingInterceptor = HttpLoggingInterceptor()
    private val baseUrl = "http://motor.yenjoy.in/"
    private var gson = GsonBuilder()
        .setLenient()
        .create()

    private val builder = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))


    private fun createClientAuth(): OkHttpClient {
        //ADD DISPATCHER WITH MAX REQUEST TO 1
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1
        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)
            .build()
    }

    private val retrofit: Retrofit = builder.client(createClientAuth()).build()

    init {
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        }
    }

}