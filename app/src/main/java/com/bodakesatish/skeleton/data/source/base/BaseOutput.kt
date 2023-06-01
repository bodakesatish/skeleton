package com.bodakesatish.skeleton.data.source.base

sealed class BaseOutput<out T : Any>{
    data class Success<out T : Any>(val code: Int, val output : T?) : BaseOutput<T>()
    data class Error(val code: Int)  : BaseOutput<Nothing>()
}
