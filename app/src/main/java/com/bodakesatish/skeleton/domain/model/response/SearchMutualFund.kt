package com.bodakesatish.skeleton.domain.model.response

import com.bodakesatish.skeleton.domain.model.base.BaseResponse

data class SearchMutualFund(
    val schemeCode: String,
    val schemeName: String
) : BaseResponse