package com.bodakesatish.skeleton.domain.model.request

import com.bodakesatish.skeleton.domain.model.base.BaseRequest
import javax.inject.Inject

open class SchemeMetaDataRequest @Inject constructor() : BaseRequest {
    var schemeCode: Int = 0
    var schemeLastSyncDate: String = ""
}