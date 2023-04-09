package com.junhua.myapplication

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Failed(val code: Int = -1, val message: String = "") : Result<Nothing>()
    data class Error(val throwable: Throwable) : Result<Nothing>()
    object Logout : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data= $data]"
            is Failed -> "Failed[code= $code， message=${message}]"
            is Error -> "Error[throwable= $throwable]"
            Logout -> "Logout[]"
        }
    }
}