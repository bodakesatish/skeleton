package com.bodakesatish.skeleton.domain.model.response

import com.bodakesatish.skeleton.domain.model.base.BaseResponse

data class UserScheme(
    val id: Int,
    val schemeCode: Int,
    val schemeName: String,
    val investment: Double,
    val currentValue: Double,
    val totalUnits: Int,
    val averageNav: Double,
    val returns: Double,
    val currentDate: String
) : BaseResponse