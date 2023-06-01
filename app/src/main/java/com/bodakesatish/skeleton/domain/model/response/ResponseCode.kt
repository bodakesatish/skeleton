package com.bodakesatish.skeleton.domain.model.response

sealed class ResponseCode {
    object Success : ResponseCode()
    object Empty : ResponseCode()
    object Network : ResponseCode()
    object Authentication : ResponseCode()
    object Fail : ResponseCode()
}
