package com.bodakesatish.skeleton.data.mapper.local

import com.bodakesatish.skeleton.data.mapper.base.BaseMapper
import com.bodakesatish.skeleton.data.source.local.entity.UserSchemesData
import com.bodakesatish.skeleton.domain.model.response.UserScheme
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSchemeDataEntityLocalMapper
@Inject constructor() : BaseMapper<UserScheme, UserSchemesData>() {

    override fun reverse(model: UserScheme): UserSchemesData {
        return UserSchemesData(
            model.id,
            model.schemeCode,
            model.schemeName,
            model.investment,
            model.currentValue,
            model.totalUnits,
            model.averageNav,
            model.returns,
            model.currentDate
        )
    }

    override fun map(entity: UserSchemesData): UserScheme {
        return UserScheme(
            entity.id,
            entity.schemeCode,
            entity.schemeName,
            entity.investment,
            entity.currentValue,
            entity.totalUnits,
            entity.averageNav,
            entity.returns,
            entity.currentDate
        )
    }
}