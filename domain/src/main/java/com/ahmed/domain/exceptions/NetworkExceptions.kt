package com.ahmed.domain.exceptions

sealed class NetworkExceptions : Exception() {
    data object UnknownException : NetworkExceptions()
    data object ServerException : NetworkExceptions()
    data object NotFoundException : NetworkExceptions()
    data object TimeoutException : NetworkExceptions()
    data object ConnectionException : NetworkExceptions()
    data object AuthorizationException : NetworkExceptions()
    data class CustomException(val msg: String) : NetworkExceptions()
    data class NeedActiveException(val msg: String) : NetworkExceptions()
    data class NeedApprovalException(val msg: String) : NetworkExceptions()
}