package com.bodakesatish.skeleton.domain.usecases

import com.bodakesatish.skeleton.domain.model.request.SearchSchemeRequest
import com.bodakesatish.skeleton.domain.model.response.SearchMutualFund
import com.bodakesatish.skeleton.domain.repository.SchemeRepository
import com.bodakesatish.skeleton.domain.usecases.base.BaseUseCase
import javax.inject.Inject

class SearchMutualFundUseCase @Inject constructor(private val schemeRepository: SchemeRepository):
BaseUseCase<SearchMutualFundUseCase.Request, SearchMutualFundUseCase.Response, SearchSchemeRequest, List<SearchMutualFund>>() {

    override suspend fun buildUseCase(request: Request): Response {
        return schemeRepository.getSearchMutualFundList(request)
    }

    class Request : BaseUseCase.Request<SearchSchemeRequest>()

    class Response : BaseUseCase.Response<List<SearchMutualFund>>()
}
