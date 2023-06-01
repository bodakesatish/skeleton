package com.bodakesatish.skeleton.data.repository

import com.bodakesatish.skeleton.data.mapper.base.BaseOutputRemoteMapper
import com.bodakesatish.skeleton.data.source.base.BaseOutput
import com.bodakesatish.skeleton.data.source.local.datasource.MutualFundDataSourceLocal
import com.bodakesatish.skeleton.data.source.remote.datasource.SchemeDataSourceRemote
import com.bodakesatish.skeleton.domain.model.response.*
import com.bodakesatish.skeleton.domain.repository.SchemeRepository
import com.bodakesatish.skeleton.domain.usecases.SchemeMetaDataUseCase
import com.bodakesatish.skeleton.domain.usecases.SearchMutualFundUseCase
import javax.inject.Inject

class SchemeRepositoryImpl
@Inject
constructor(
    private val remoteDataSource: SchemeDataSourceRemote,
    private val localDataSource: MutualFundDataSourceLocal
) : SchemeRepository {

    override suspend fun getSearchMutualFundList(request: SearchMutualFundUseCase.Request): SearchMutualFundUseCase.Response {
        val output = remoteDataSource.getSearchMutualFundList(request)
        val response = SearchMutualFundUseCase.Response()
        val baseOutputMapper = BaseOutputRemoteMapper<List<SearchMutualFund>>()
        baseOutputMapper.mapBaseOutput(output, response,
            executeOnSuccess ={
                localDataSource.insertAll(it)
                val localOutput = localDataSource.getSearchMutualFundList(request)
                if(localOutput is BaseOutput.Success) {
                    return@mapBaseOutput localOutput.output!!
                }
                else {
                    return@mapBaseOutput emptyList()
                }
            },
            executeOnError = {
                val localOutput = localDataSource.getSearchMutualFundList(request)
                if(localOutput is BaseOutput.Success) {
                    return@mapBaseOutput localOutput.output!!
                }
                else {
                    return@mapBaseOutput emptyList()
                }
            })
        return response
    }

    override suspend fun getSchemeMetaData(request: SchemeMetaDataUseCase.Request): SchemeMetaDataUseCase.Response {

        val localOutput = localDataSource.getSchemeMetaBySchemeCoder(request)
        val response = SchemeMetaDataUseCase.Response()
        val baseOutputMapper = BaseOutputRemoteMapper<SchemeMetaResponse>()
        var flagGetFromServer = true
        var schemeMetaData  = SchemeMeta()
        var schemeNavList : List<SchemeNavDataResponse> = emptyList()
        var status = "FAIL"
        if(localOutput.isNotEmpty() ) {
            flagGetFromServer = request.getRequestModel().schemeLastSyncDate != localOutput[0].schemeLastSyncedDate
            schemeMetaData =  localOutput[0]
            val schemeNavListResponse = localDataSource.getSchemeNavData(request)
            if(schemeNavListResponse is BaseOutput.Success) {
               schemeNavList = schemeNavListResponse.output!!
            }
            status = "SUCCESS"
        } else {
            status = "FAIL"
        }

        var schemeMetaResponse : SchemeMetaResponse =  SchemeMetaResponse(schemeNavList, schemeMetaData, status)

        if(flagGetFromServer) {
            val output = remoteDataSource.getSchemeMetaData(request)

            baseOutputMapper.mapBaseOutput(output, response,
                executeOnSuccess = {
                    localDataSource.deleteSchemeNavData(request)
                    localDataSource.insertSchemeNavData(it.data,request.getRequestModel().schemeCode)
                    it.meta.schemeLastSyncedDate = request.getRequestModel().schemeLastSyncDate
                    localDataSource.insertSchemeData(it.meta)
                    status = "SUCCESS"
                    it
                },
                executeOnError ={
                    schemeMetaResponse = SchemeMetaResponse(schemeNavList, schemeMetaData, status)
                    return@mapBaseOutput schemeMetaResponse
                })
        } else {
            response.setData(schemeMetaResponse)
        }
        if(status == "SUCCESS") {
            response.setResponseCode(ResponseCode.Success)
        } else {
            response.setResponseCode(ResponseCode.Fail)
        }

        return response
    }

    override suspend fun getUserScheme(schemeCode: Int): UserScheme {
        return localDataSource.getUserScheme(schemeCode)
    }

    override suspend fun addSchemeToUserPortfolio(userScheme: UserScheme) {
        localDataSource.insertSchemeToUserPortfolio(userScheme)
    }

    override suspend fun removeSchemeToUserPortfolio(schemeCode: Int) {
        localDataSource.deleteSchemeFromUserPortfolio(schemeCode)
    }

    override suspend fun getUserSchemes(): List<UserScheme> {
       return localDataSource.getUserSchemes()
    }

    override suspend fun insertUserSchemeTransaction(transaction: UserSchemeTransaction) {
        localDataSource.insertUserSchemeTransaction(transaction)
    }

    override suspend fun getUserSchemeTransactionList(schemeCode: Int): List<UserSchemeTransaction> {
        return localDataSource.getUserSchemeTransactionList(schemeCode)
    }

}