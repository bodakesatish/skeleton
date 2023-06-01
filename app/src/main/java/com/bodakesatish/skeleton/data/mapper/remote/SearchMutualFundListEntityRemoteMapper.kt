package com.bodakesatish.skeleton.data.mapper.remote

import com.bodakesatish.skeleton.data.mapper.base.BaseMapper
import com.bodakesatish.skeleton.data.source.remote.entity.MutualFundEntity
import com.bodakesatish.skeleton.domain.model.response.SearchMutualFund
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchMutualFundListEntityRemoteMapper
@Inject constructor() : BaseMapper<SearchMutualFund, MutualFundEntity>() {

    override fun map(entity: MutualFundEntity): SearchMutualFund {
        return SearchMutualFund(
            entity.schemeCode.toString(),
            entity.schemeName
        )
    }
}