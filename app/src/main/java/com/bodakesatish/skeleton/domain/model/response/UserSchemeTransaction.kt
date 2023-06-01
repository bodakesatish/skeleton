package com.bodakesatish.skeleton.domain.model.response

import com.bodakesatish.skeleton.domain.model.base.BaseResponse

data class UserSchemeTransaction(
    val id: Int,
    val schemeCode: Int,
    val investment: String,
    val units: String,
    val nav: String,
    val transactionDate: String
) : BaseResponse