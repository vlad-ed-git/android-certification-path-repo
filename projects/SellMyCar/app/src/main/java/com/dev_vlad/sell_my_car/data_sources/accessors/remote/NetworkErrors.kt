package com.dev_vlad.sell_my_car.data_sources.accessors.remote

import com.dev_vlad.sell_my_car.R

object NetworkErrors {

    const val UNABLE_TO_RESOLVE_HOST = R.string.unable_to_resolve_host_err
    const val UNABLE_TODO_OPERATION_WO_INTERNET = R.string.internet_required_err
    const val ERROR_CHECK_NETWORK_CONNECTION = R.string.check_internet_connection
    const val NETWORK_ERROR_UNKNOWN = R.string.unknown_network_err
    const val NETWORK_ERROR = R.string.network_err_occurred
    const val NETWORK_ERROR_TIMEOUT = R.string.network_timeout_err
    const val NETWORK_DATA_NULL = R.string.network_data_null_err


    fun isNetworkError(msgRes: Int): Boolean{
        return when (msgRes) {
            UNABLE_TO_RESOLVE_HOST -> true
            else -> false
        }
    }
}