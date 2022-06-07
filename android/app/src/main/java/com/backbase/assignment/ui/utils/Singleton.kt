package com.backbase.assignment.ui.utils


object Singleton {
    var isNetworkAvailable:Boolean=true
    const val  APPLICATION_JSON="application/json"
    const val  ACCEPT_KEY = "Accept"
    const  val CONNECT_TIMEOUT = 15
    const val READ_TIMEOUT = 60
    const val WRITE_TIMEOUT = 60
    const val APIKEY="55957fcf3ba81b137f8fc01ac5a31fb5"
    var baseUrl="https://api.themoviedb.org/"
    var imageBaseUrl="https://image.tmdb.org/t/p/original/"
}