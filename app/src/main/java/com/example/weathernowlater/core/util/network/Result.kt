package com.example.weathernowlater.core.util.network

import com.example.mealzapp.meals.domain.util.Error

typealias DomainError = Error

sealed interface Result<out D, out E: Error> {
    data class Success<out D>(val data: D): Result<D, Nothing>
    data class Error<out E: DomainError>(val error: E): Result<Nothing, E>
}
