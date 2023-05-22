package com.bodakesatish.skeleton.data.mapper.local

import com.bodakesatish.skeleton.data.mapper.base.BaseMapper
import com.bodakesatish.skeleton.data.source.local.entity.UserMutualFundSummaryData
import com.bodakesatish.skeleton.domain.model.response.UserMutualFundSummary
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserMutualFundSummaryEntityLocalMapper
@Inject constructor() : BaseMapper<UserMutualFundSummary, UserMutualFundSummaryData>() {

    override fun reverse(model: UserMutualFundSummary): UserMutualFundSummaryData? {
        return UserMutualFundSummaryData(
            model.id,
            model.investment,
            model.currentValue,
            model.returns,
            model.valueDate,
            model.returnsPercentage,
            model.currentPercentage,
            model.returnDate
        )
    }

    override fun map(entity: UserMutualFundSummaryData): UserMutualFundSummary {
        return UserMutualFundSummary(
            entity.id,
            entity.investment,
            entity.currentValue,
            entity.returns,
            entity.valueDate,
            entity.returnsPercentage,
            entity.currentPercentage,
            entity.returnDate
        )
    }
}