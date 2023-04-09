package com.junhua.myapplication

sealed class MyResponse<out T> {
    companion object {
        const val CODE_SUCCESS = 0
        const val CODE_FAIL = 9000
        fun <T> createMyResponse(code: Int, message: String, data: T?): MyResponse<T> =
            when (code) {
                CODE_SUCCESS -> Success(code, message, data)
                else -> ExpectedError(code, message, data)
            }
    }

    /**
     * 响应成功对应的返回类型
     */
    data class Success<out T>(val code: Int, val message: String, val data: T?) : MyResponse<T>()

    /**
     * 响应出现意料之内的错误
     */
    data class ExpectedError<out T>(val code: Int, val message: String, val data: T?) :
        MyResponse<T>()

    /**
     * 响应意料之外的错误
     */
    data class UnexpectedError(val throwable: Throwable) : MyResponse<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[code =$code ,message=${message}, data= ${data}]"
            is ExpectedError -> "ExpectedError[code=$code,message=$message , data=$data"
            is UnexpectedError -> "UnexpectedError[throwable:$throwable]"
        }
    }
}