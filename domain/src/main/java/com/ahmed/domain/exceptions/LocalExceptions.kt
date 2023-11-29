package com.ahmed.domain.exceptions

sealed class LocalExceptions : Exception() {
    data object UnknownException : LocalExceptions()
    data object TimeoutException : LocalExceptions()
}