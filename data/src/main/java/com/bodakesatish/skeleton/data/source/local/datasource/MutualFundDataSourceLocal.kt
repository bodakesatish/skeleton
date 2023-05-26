package com.bodakesatish.skeleton.data.source.local.datasource

import com.bodakesatish.skeleton.data.source.base.DataResponseCode
import com.bodakesatish.skeleton.data.source.DataSource
import com.bodakesatish.skeleton.data.mapper.local.*
import com.bodakesatish.skeleton.data.source.base.BaseOutput
import com.bodakesatish.skeleton.data.source.local.dao.*
import com.bodakesatish.skeleton.data.source.local.entity.UserSchemesData
import com.bodakesatish.skeleton.domain.model.response.*
import com.bodakesatish.skeleton.domain.usecases.SchemeMetaDataUseCase
import com.bodakesatish.skeleton.domain.usecases.SearchMutualFundUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MutualFundDataSourceLocal
@Inject
constructor(
    private val searchMFDao: SearchMutualFundDao,
    private val profileDao: UserMutualFundSummaryDao,
    private val schemeNavDataDao: SchemeNavDataDao,
    private val schemeMetaDataDao: SchemeMetaDataDao,
    private val userSchemesDao: UserSchemesDao,
    private val userSchemeTransaction: UserSchemeTransactionDao,
    private val mapper: UserMutualFundSummaryEntityLocalMapper,
    private val searchMfMapper: SearchMutualFundEntityLocalMapper,
    private val navMapper: SchemeNavDataEntityLocalMapper,
    private val schemeMetaDataEntityLocalMapper: SchemeMetaDataEntityLocalMapper,
    private val userSchemeDataEntityLocalMapper: UserSchemeDataEntityLocalMapper,
    private val userSchemeTransactionLocalMapper: UserSchemeTransactionLocalMapper
) :
    DataSource.SchemeDataStore {

    override suspend fun getUserMutualFundSummary(): BaseOutput<UserMutualFundSummary> {
        val data = profileDao.getUserMutualFundSummary()
        return if(data.isNotEmpty()) {
            BaseOutput.Success(DataResponseCode.SUCCESS, mapper.map(data[0]))
        } else {
            BaseOutput.Success(DataResponseCode.EMPTY, UserMutualFundSummary(0,"none","","","","","",""))
        }
    }

    override suspend fun insertUserMutualFundSummary(profile: UserMutualFundSummary) {
        val data = mapper.reverse(profile)
        data?.let {
            profileDao.insert(data)
        }
    }

    override suspend fun getSearchMutualFundList(request: SearchMutualFundUseCase.Request): BaseOutput<List<SearchMutualFund>> {
        val data = searchMFDao.getMutualFundList(request.getRequestModel().input)
        return if(data.isNotEmpty()) {
            BaseOutput.Success(DataResponseCode.SUCCESS, searchMfMapper.map(data))
        } else {
            BaseOutput.Success(DataResponseCode.EMPTY, emptyList())
        }
    }

    override suspend fun getSchemeMetaData(request: SchemeMetaDataUseCase.Request): BaseOutput<SchemeMetaResponse> {
       return BaseOutput.Success(DataResponseCode.SUCCESS, SchemeMetaResponse(emptyList(),SchemeMeta(),""))
    }

    override suspend fun getSchemeMetaData(): SchemeMeta {
        val listData = schemeMetaDataDao.getShcemeMetaData().get(0)
        val schemeMetaResponse = SchemeMeta(listData.fundHouse,listData.schemeCategory,listData.schemeCode,listData.schemeName,listData.schemeType)
        return schemeMetaResponse
    }

    override suspend fun insertSchemeNavData(
        data: List<SchemeNavDataResponse>,
        schemeCode: Int
    ) {
       data.forEach { it.schemeCode = schemeCode }
       val listData = navMapper.reverse(data)
       schemeNavDataDao.insertAll(listData)
    }

    override suspend fun getSchemeNavData(request: SchemeMetaDataUseCase.Request): BaseOutput<List<SchemeNavDataResponse>> {
        val data = schemeNavDataDao.getSchemeNavDataBySchemeCode(request.getRequestModel().schemeCode)
        return if(data.isNotEmpty()) {
            BaseOutput.Success(DataResponseCode.SUCCESS, navMapper.map(data))
        } else {
            BaseOutput.Success(DataResponseCode.EMPTY, emptyList())
        }
    }

    override suspend fun getUserScheme(schemeCode: Int): UserScheme {
        val userScheme = userSchemesDao.getUserSchemeBy(schemeCode)
        return UserScheme(
                userScheme?.id ?: 0,
                userScheme?.schemeCode ?: 0,
                userScheme?.schemeName ?: "",
                userScheme?.investment ?: 0.0,
                userScheme?.currentValue ?: 0.0,
                userScheme?.totalUnits ?: 0,
                userScheme?.averageNav ?: 0.0,
                userScheme?.returns ?: 0.0,
                userScheme?.currentDate ?: ""
            )
    }

    override suspend fun insertSchemeToUserPortfolio(userScheme: UserScheme) {
       userSchemesDao.insert(UserSchemesData(0,userScheme.schemeCode,userScheme.schemeName,
                userScheme.investment,userScheme.currentValue,userScheme.totalUnits,userScheme.averageNav,
            userScheme.returns, userScheme.currentDate))
    }

    override suspend fun insertUserSchemeTransaction(transaction: UserSchemeTransaction) {
        userSchemeTransaction.insert(userSchemeTransactionLocalMapper.reverse(transaction))
    }

    override suspend fun deleteSchemeFromUserPortfolio(schemeCode: Int) {
        userSchemesDao.deleteScheme(schemeCode)
    }

    override suspend fun getUserSchemes(): List<UserScheme> {
        val response = userSchemesDao.getAllUserSchemes()
        return userSchemeDataEntityLocalMapper.map(response)
    }

    suspend fun insertAll(receipts: List<SearchMutualFund>) {
        val data = searchMfMapper.reverse(receipts)
        searchMFDao.insertAll(data)
    }

    suspend fun getSchemeMetaBySchemeCoder(request: SchemeMetaDataUseCase.Request): List<SchemeMeta> {
        val schemeMetaData =  schemeMetaDataDao.getSchemeMetaDataBySchemeCode(request.getRequestModel().schemeCode.toString())
        return schemeMetaDataEntityLocalMapper.map(schemeMetaData)
    }

    suspend fun deleteSchemeNavData(request: SchemeMetaDataUseCase.Request) {
        schemeNavDataDao.deleteBySchemeCode(request.getRequestModel().schemeCode)
    }

    suspend fun insertSchemeData(schemeMeta: SchemeMeta) {
        schemeMetaDataDao.insert(schemeMetaDataEntityLocalMapper.reverse(schemeMeta))
    }

    suspend fun getUserSchemeTransactionList(schemeCode: Int): List<UserSchemeTransaction> {
        return userSchemeTransactionLocalMapper.map(userSchemeTransaction.getUserSchemeTransactionBySchemeCode(schemeCode))
    }

}