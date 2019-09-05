package com.vidyacharan.urbandictionary.util.common

class ResultState<out T> private constructor(val status: Status, val data: T?) {

    companion object {
        fun <T> success(data: T? = null): ResultState<T> = ResultState(Status.SUCCESS, data)

        fun <T> error(data: T? = null): ResultState<T> = ResultState(Status.ERROR, data)

        fun <T> loading(data: T? = null): ResultState<T> = ResultState(Status.LOADING, data)

        fun <T> unknown(data: T? = null): ResultState<T> = ResultState(Status.UNKNOWN, data)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    UNKNOWN
}