package com.bodakesatish.skeleton.helper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class CoroutineHelper : CoroutineScope {

    private val parentJob = Job()

    override val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO

    private val scope = CoroutineScope(coroutineContext)

    fun getScope(): CoroutineScope {
        return scope
    }
}