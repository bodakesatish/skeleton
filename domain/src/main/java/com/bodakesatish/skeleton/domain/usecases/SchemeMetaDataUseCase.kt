package com.bodakesatish.skeleton.domain.usecases

import com.bodakesatish.skeleton.domain.model.request.SchemeMetaDataRequest
import com.bodakesatish.skeleton.domain.model.response.SchemeMetaResponse
import com.bodakesatish.skeleton.domain.repository.SchemeRepository
import com.bodakesatish.skeleton.domain.usecases.base.BaseUseCase
import javax.inject.Inject

class SchemeMetaDataUseCase @Inject constructor(private val schemeRepository: SchemeRepository):
BaseUseCase<SchemeMetaDataUseCase.Request, SchemeMetaDataUseCase.Response, SchemeMetaDataRequest, SchemeMetaResponse>() {

    override suspend fun buildUseCase(request: Request): Response {
        return schemeRepository.getSchemeMetaData(request)
    }

    class Request : BaseUseCase.Request<SchemeMetaDataRequest>()

    class Response : BaseUseCase.Response<SchemeMetaResponse>()
}
