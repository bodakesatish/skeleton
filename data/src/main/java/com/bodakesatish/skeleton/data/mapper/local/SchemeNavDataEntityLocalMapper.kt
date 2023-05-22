package com.bodakesatish.skeleton.data.mapper.local

import com.bodakesatish.skeleton.data.mapper.base.BaseMapper
import com.bodakesatish.skeleton.data.source.local.entity.SchemeNavData
import com.bodakesatish.skeleton.domain.model.response.SchemeNavDataResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchemeNavDataEntityLocalMapper
@Inject constructor() : BaseMapper<SchemeNavDataResponse, SchemeNavData>() {

    override fun reverse(model: SchemeNavDataResponse): SchemeNavData {
        return SchemeNavData(
            0,
            schemeCode = model.schemeCode,
            navDate = model.date,
            navValue = model.nav
        )
    }

    override fun map(entity: SchemeNavData): SchemeNavDataResponse {
        return SchemeNavDataResponse(
            entity.navDate,
            entity.navValue,
            entity.schemeCode
        )
    }
}