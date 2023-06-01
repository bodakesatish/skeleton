package com.bodakesatish.skeleton.domain.repository

import com.bodakesatish.skeleton.domain.model.response.UserScheme
import com.bodakesatish.skeleton.domain.model.response.UserSchemeTransaction
import com.bodakesatish.skeleton.domain.usecases.SchemeMetaDataUseCase
import com.bodakesatish.skeleton.domain.usecases.SearchMutualFundUseCase

interface SchemeRepository {
    suspend fun getSearchMutualFundList(request: SearchMutualFundUseCase.Request): SearchMutualFundUseCase.Response
    suspend fun getSchemeMetaData(request: SchemeMetaDataUseCase.Request): SchemeMetaDataUseCase.Response
    suspend fun getUserScheme(schemeCode: Int): UserScheme
    suspend fun addSchemeToUserPortfolio(userScheme: UserScheme)
    suspend fun removeSchemeToUserPortfolio(schemeCode: Int)
    suspend fun getUserSchemes(): List<UserScheme>
    suspend fun insertUserSchemeTransaction(transaction: UserSchemeTransaction)
    suspend fun getUserSchemeTransactionList(schemeCode: Int): List<UserSchemeTransaction>
}