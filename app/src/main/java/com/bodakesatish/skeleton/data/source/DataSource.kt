package com.bodakesatish.skeleton.data.source

import com.bodakesatish.skeleton.data.source.base.BaseOutput
import com.bodakesatish.skeleton.domain.model.response.*
import com.bodakesatish.skeleton.domain.usecases.SchemeMetaDataUseCase
import com.bodakesatish.skeleton.domain.usecases.SearchMutualFundUseCase

interface DataSource {

    interface ProfileDataStore {
        suspend fun getProfile(): BaseOutput<Profile>
        suspend fun insertProfile(profile: Profile)
    }

    interface SchemeDataStore {

        suspend fun insertUserMutualFundSummary(profile: UserMutualFundSummary)
        suspend fun insertSchemeNavData(data: List<SchemeNavDataResponse>, schemeCode: Int)
        suspend fun insertSchemeToUserPortfolio(userScheme: UserScheme)
        suspend fun insertUserSchemeTransaction(transaction: UserSchemeTransaction)

        suspend fun getSchemeMetaData(request: SchemeMetaDataUseCase.Request): BaseOutput<SchemeMetaResponse>
        suspend fun getUserMutualFundSummary(): BaseOutput<UserMutualFundSummary>

        suspend fun getSearchMutualFundList(request: SearchMutualFundUseCase.Request): BaseOutput<List<SearchMutualFund>>
        suspend fun getSchemeNavData(request: SchemeMetaDataUseCase.Request): BaseOutput<List<SchemeNavDataResponse>>
        suspend fun getUserSchemes(): List<UserScheme>

        suspend fun getSchemeMetaData(): SchemeMeta
        suspend fun getUserScheme(schemeCode: Int): UserScheme

        suspend fun deleteSchemeFromUserPortfolio(schemeCode: Int)
    }
}