package com.ahmed.orangechallenge.ui.base

import com.ahmed.domain.exceptions.NetworkExceptions
import com.ahmed.domain.util.DataState
import com.ahmed.orangechallenge.R

interface NetworkExtensionsActions {
    fun onLoad(showLoading: Boolean) {

    }

    fun onCommonError(exceptionMsgId: Int) {

    }

    fun onShowSuccessToast(msg: String?) {

    }

    fun onFail(msg: String?) {

    }

    fun authorizationFail() {

    }

    fun block() {

    }

    fun authorizationNeedActive(msg: String) {

    }

    fun needApproval(msg: String?) {

    }
}

fun <T> DataState<T>.applyCommonSideEffects(
    networkExtensionsActions: NetworkExtensionsActions,
    showLoading: Boolean = true,
    showSuccessToast: Boolean = false,
    cancelNotActive: Boolean = false,
    onSuccess: (T) -> Unit = {},
) {
    when (this) {
        is DataState.Loading -> {
            if (showLoading) networkExtensionsActions.onLoad(true)
        }

        is DataState.Success -> {
            networkExtensionsActions.onLoad(false)

            onSuccess(this.data)

        }

        is DataState.Error -> {
            networkExtensionsActions.onLoad(false)
            handleError(networkExtensionsActions, throwable)
        }

        DataState.Idle -> {
            networkExtensionsActions.onLoad(false)
        }
    }
}

private fun handleError(
    networkExtensionsActions: NetworkExtensionsActions,
    throwable: Throwable,
) {
    when (throwable) {
        is NetworkExceptions.AuthorizationException -> {
            networkExtensionsActions.authorizationFail()
        }

        is NetworkExceptions.NeedActiveException -> {
            networkExtensionsActions.authorizationNeedActive(throwable.msg)
        }

        is NetworkExceptions.NeedApprovalException -> {
            networkExtensionsActions.needApproval(throwable.msg)
        }

        is NetworkExceptions.ConnectionException -> {
            networkExtensionsActions.onCommonError(R.string.no_internet_connection)
        }

        is NetworkExceptions.CustomException -> {
            networkExtensionsActions.onFail(throwable.msg)
        }

        else -> {
            networkExtensionsActions.onCommonError(throwable.getIsCommonException())
        }
    }
}

fun Throwable.getIsCommonException(): Int {
    when (this) {
        is NetworkExceptions.ConnectionException -> {
            return R.string.no_internet_connection
        }

        is NetworkExceptions.NotFoundException -> {
            return R.string.something_went_wrong
        }

        is NetworkExceptions.ServerException -> {
            return R.string.something_went_wrong
        }

        is NetworkExceptions.TimeoutException -> {
            return R.string.no_internet_connection
        }

        is NetworkExceptions.UnknownException -> {
            return R.string.something_went_wrong
        }

        else -> {
            return R.string.something_went_wrong
        }
    }
}