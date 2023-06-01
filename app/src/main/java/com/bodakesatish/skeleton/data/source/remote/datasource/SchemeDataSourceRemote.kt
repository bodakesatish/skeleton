package com.bodakesatish.skeleton.data.source.remote.datasource

import com.bodakesatish.skeleton.data.source.DataSource
import com.bodakesatish.skeleton.data.mapper.remote.SchemeMetaDataEntityRemoteMapper
import com.bodakesatish.skeleton.data.mapper.remote.SearchMutualFundListEntityRemoteMapper
import com.bodakesatish.skeleton.data.source.base.BaseOutput
import com.bodakesatish.skeleton.data.source.remote.datasource.base.BaseDataSourceRemote
import com.bodakesatish.skeleton.domain.model.base.BaseRequest
import com.bodakesatish.skeleton.domain.model.response.*
import com.bodakesatish.skeleton.domain.usecases.SchemeMetaDataUseCase
import com.bodakesatish.skeleton.domain.usecases.SearchMutualFundUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchemeDataSourceRemote
@Inject
constructor(
    private val appService: AppService,
    private val mapper: SearchMutualFundListEntityRemoteMapper,
    private val schemeMapper: SchemeMetaDataEntityRemoteMapper
    ) : BaseDataSourceRemote<BaseRequest>(),
        DataSource.SchemeDataStore
{
    override suspend fun getUserMutualFundSummary(): BaseOutput<UserMutualFundSummary> {
        TODO("Not yet implemented")
    }

    override suspend fun insertUserMutualFundSummary(profile: UserMutualFundSummary) {
        TODO("Not yet implemented")
    }

    override suspend fun getSearchMutualFundList(request: SearchMutualFundUseCase.Request): BaseOutput<List<SearchMutualFund>> {
        val response = sendRequest { appService.getSearchMutualFundList(request.getRequestModel().input) }
        return getOutput(response) {mapper.map(response.body()!!)}
    }

    override suspend fun getSchemeMetaData(request: SchemeMetaDataUseCase.Request): BaseOutput<SchemeMetaResponse> {
        val response = sendRequest { appService.getSchemeMetaData(request.getRequestModel().schemeCode) }
        val schemeMetaData : SchemeMetaResponse
        if(response.body() != null) {
            val schemeMetaEntity = response.body()!!
            val schemeData = schemeMapper.map(schemeMetaEntity.data)
            val meta = SchemeMeta(
                schemeMetaEntity.meta.fund_house,
                schemeMetaEntity.meta.scheme_category,
                schemeMetaEntity.meta.scheme_code,
                schemeMetaEntity.meta.scheme_name,
                schemeMetaEntity.meta.scheme_type
            )
            schemeMetaData = SchemeMetaResponse(schemeData, meta, schemeMetaEntity.status)
        } else {
            schemeMetaData = SchemeMetaResponse(emptyList(), SchemeMeta(), "FAIL")
        }
        return getOutput(response) { schemeMetaData }

    }

    override suspend fun getSchemeMetaData(): SchemeMeta {
        TODO("Not yet implemented")
    }

    override suspend fun insertSchemeNavData(
        data: List<SchemeNavDataResponse>,
        schemeCode: Int
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getSchemeNavData(request: SchemeMetaDataUseCase.Request): BaseOutput<List<SchemeNavDataResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserScheme(schemeCode: Int): UserScheme {
        return UserScheme(
            0,
        0,
        "",
            0.0,
            0.0,
            0,
            0.0,
            0.0,
            ""
        )
    }

    override suspend fun insertSchemeToUserPortfolio(userScheme: UserScheme) {

    }

    override suspend fun insertUserSchemeTransaction(transaction: UserSchemeTransaction) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSchemeFromUserPortfolio(schemeCode: Int) {

    }

    override suspend fun getUserSchemes(): List<UserScheme> {
        return emptyList()
    }
}