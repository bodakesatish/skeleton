package com.bodakesatish.skeleton.data.mapper.base

import com.bodakesatish.skeleton.data.source.remote.entity.BaseEntity
import com.bodakesatish.skeleton.domain.model.base.BaseResponse
import java.util.*

abstract class BaseMapper<P : BaseResponse, Q : BaseEntity> {

    abstract fun map(entity: Q): P
    open fun reverse(model: P): Q? {
        return null
    }

    fun map(list: List<Q>?): List<P> {
        if (list != null) {
            val result = ArrayList<P>(list.size)
            list.mapTo(result) { map(it) }
            return result
        }
        return emptyList()
    }

    fun reverse(list: List<P>?): List<Q> {
        if (list != null) {
            val result = ArrayList<Q>(list.size)
            list.mapNotNullTo(result) { reverse(it) }
            return result
        }
        return emptyList()
    }
}
