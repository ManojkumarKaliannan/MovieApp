package com.backbase.assignment.ui.utils


object Singleton {
    var connectionCheck:Boolean=false
    const val  APPLICATION_JSON="application/json"
    const val  ACCEPT_KEY = "Accept"
    const  val CONNECT_TIMEOUT = 15
    const val READ_TIMEOUT = 60
    const val WRITE_TIMEOUT = 60
    var baseUrl="https://api.themoviedb.org/"
    var imageBaseUrl="https://image.tmdb.org/t/p/original/"
}