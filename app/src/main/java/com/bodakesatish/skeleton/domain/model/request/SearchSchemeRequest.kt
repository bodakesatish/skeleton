package com.bodakesatish.skeleton.domain.model.request

import com.bodakesatish.skeleton.domain.model.base.BaseRequest
import javax.inject.Inject

open class SearchSchemeRequest @Inject constructor() : BaseRequest {
    var input: String = ""
}