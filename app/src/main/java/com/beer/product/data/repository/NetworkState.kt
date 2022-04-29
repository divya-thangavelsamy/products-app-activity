package com.beer.product.data.repository

sealed class ResultOf<out T> {
    data class Success<out R>(val value: R): ResultOf<R>()
    object Loading : ResultOf<Nothing>()
    data class Failure(
        val throwable: Throwable?
    ): ResultOf<Nothing>()
}

