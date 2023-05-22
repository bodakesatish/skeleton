package com.bodakesatish.skeleton.domain.model.response

import com.bodakesatish.skeleton.domain.model.base.BaseResponse

data class UserMutualFundSummary(
    val id: Int,
    val investment: String,
    val currentValue: String,
    val returns: String,
    val valueDate: String,
    val returnsPercentage: String,
    val currentPercentage: String,
    val returnDate: String
) : BaseResponse