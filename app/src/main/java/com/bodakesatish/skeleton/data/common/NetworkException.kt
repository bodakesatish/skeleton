package com.bodakesatish.skeleton.data.common

import java.io.IOException

class NetworkException : IOException() {

    override fun getLocalizedMessage(): String {
        return "NetworkException"
    }
}