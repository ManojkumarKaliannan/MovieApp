package com.backbase.assignment.ui.network.model

import com.google.gson.annotations.SerializedName

data class Errors(@SerializedName("message")
                  val message: String)