package com.beer.product.data.repository

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

class NetworkState(val status: Status) {

    companion object {
        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState

        init {
            LOADED = NetworkState(Status.SUCCESS)
            LOADING = NetworkState(Status.RUNNING)
            ERROR = NetworkState(Status.FAILED)
        }
    }
}
