package com.example.madcourse.domain.network.utils

sealed class NetworkResult<out T:Any> {
    data class Success<T : Any> (val result : T) : NetworkResult<T>()
    data class Error (val message : String) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
    object Idle : NetworkResult<Nothing>()
}