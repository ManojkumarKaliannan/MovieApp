package com.backbase.assignment.ui.network.model

import androidx.annotation.Nullable

class Resource<T> private constructor(val status: Status, @param:Nullable @field:Nullable val data: T?,
                                      @param:Nullable @field:Nullable val message: String?) {

    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, @Nullable data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(@Nullable data: T): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <T> failure(msg: String, @Nullable data: T?): Resource<T> {
            return Resource(Status.FAILURE, data, msg)
        }
    }
}