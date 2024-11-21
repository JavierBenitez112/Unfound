package com.example.unfound.domain.model

import com.example.unfound.domain.network.util.Error

enum class DataError: Error {
    NO_INTERNET,
    GENERIC_ERROR
}

