package com.bodakesatish.skeleton.data.mapper.remote

import com.bodakesatish.skeleton.data.mapper.base.BaseMapper
import com.bodakesatish.skeleton.data.source.remote.entity.DataEntity
import com.bodakesatish.skeleton.domain.model.response.SchemeNavDataResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchemeMetaDataEntityRemoteMapper
@Inject constructor() : BaseMapper<SchemeNavDataResponse, DataEntity>() {

    override fun map(entity: DataEntity): SchemeNavDataResponse {
        return SchemeNavDataResponse(
            entity.date,
            entity.nav
        )
    }

}