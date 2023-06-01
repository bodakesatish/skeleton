package com.bodakesatish.skeleton.data.mapper.local

import com.bodakesatish.skeleton.data.mapper.base.BaseMapper
import com.bodakesatish.skeleton.data.source.local.entity.SchemeMetaData
import com.bodakesatish.skeleton.domain.model.response.SchemeMeta
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchemeMetaDataEntityLocalMapper
@Inject constructor() : BaseMapper<SchemeMeta, SchemeMetaData>() {

    override fun reverse(model: SchemeMeta): SchemeMetaData {
        return SchemeMetaData(
            model.scheme_code,
            model.scheme_name,
            model.fund_house,
            model.scheme_type,
            model.scheme_category,
            model.schemeLastSyncedDate
        )
    }

    override fun map(entity: SchemeMetaData): SchemeMeta {
        return SchemeMeta(
            entity.fundHouse,
            entity.schemeCategory,
            entity.schemeCode,
            entity.schemeName,
            entity.schemeType,
            entity.schemeLastSyncedDate
        )
    }
}