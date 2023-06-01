package com.bodakesatish.skeleton.domain.model.response

import com.bodakesatish.skeleton.domain.model.base.BaseResponse

data class Profile(
    val userId: Long,
    val name: String,
    val picture: String?
) : BaseResponse