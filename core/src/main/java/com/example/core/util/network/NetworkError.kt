package com.example.core.util.network

enum class NetworkError : Error {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET_CONNECTION,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN_ERROR,
    NO_RESULTS_FOUND
}