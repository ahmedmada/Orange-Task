package com.ahmed.data.util

import com.ahmed.data.util.FailRequestCode.BLOCKED
import com.ahmed.data.util.FailRequestCode.EXCEPTION
import com.ahmed.data.util.FailRequestCode.UN_AUTH
import com.ahmed.data.util.NetworkConstants.NETWORK_TIMEOUT
import com.ahmed.domain.exceptions.NetworkExceptions
import com.ahmed.domain.util.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): Flow<DataState<T>> = flow {
    withTimeout(NETWORK_TIMEOUT) {
        val response = apiCall.invoke()
        emit(handleSuccess(response))
    }
}.onStart {
    emit(DataState.Loading)
}.catch {
    emit(handleError(it))
}.flowOn(Dispatchers.IO)

suspend fun <T> safeLocalCall(
    apiCall: suspend () -> T
): Flow<DataState<T>> = flow {
    val response = apiCall.invoke()
    emit(handleSuccess(response))
}

fun <T> handleSuccess(response: T): DataState<T> {
    if (response != null) return DataState.Success(response)
    return DataState.Error(NetworkExceptions.UnknownException)
}

fun <T> handleError(it: Throwable): DataState<T> {
    it.printStackTrace()
    return when (it) {
        is TimeoutCancellationException -> {
            DataState.Error(NetworkExceptions.TimeoutException)
        }

        is UnknownHostException -> {
            DataState.Error(NetworkExceptions.ConnectionException)
        }

        is IOException -> {
            DataState.Error(NetworkExceptions.UnknownException)
        }

        is HttpException -> {
            DataState.Error(convertErrorBody(it))
        }

        else -> {
            DataState.Error(NetworkExceptions.UnknownException)
        }
    }
}

/**
 * BaseResponse() is a generic class which contains most common keys and to check if returning fail from the back-end e.g. not passing the validation
 **/
private fun convertErrorBody(throwable: HttpException): Exception {
    val errorBody = throwable.response()?.errorBody()?.charStream()
//    val response = Gson().fromJson(errorBody, BaseResponse::class.java)
    return when (throwable.code()) {
//        FAIL -> NetworkExceptions.CustomException(response.msg)
        UN_AUTH, BLOCKED -> NetworkExceptions.AuthorizationException
        EXCEPTION -> NetworkExceptions.ServerException
        else -> NetworkExceptions.UnknownException
    }
}