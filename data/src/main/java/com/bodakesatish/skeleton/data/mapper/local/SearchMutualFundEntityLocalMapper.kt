package com.bodakesatish.skeleton.data.mapper.local

import com.bodakesatish.skeleton.data.mapper.base.BaseMapper
import com.bodakesatish.skeleton.data.source.local.entity.SearchMutualFundData
import com.bodakesatish.skeleton.domain.model.response.SearchMutualFund
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchMutualFundEntityLocalMapper
@Inject constructor() : BaseMapper<SearchMutualFund, SearchMutualFundData>() {

    override fun reverse(model: SearchMutualFund): SearchMutualFundData {
        return SearchMutualFundData(
            model.schemeCode.toInt(),
            model.schemeName
        )
    }

    override fun map(entity: SearchMutualFundData): SearchMutualFund {
        return SearchMutualFund(
            entity.schemeCode.toString(),
            entity.schemeName
        )
    }
}