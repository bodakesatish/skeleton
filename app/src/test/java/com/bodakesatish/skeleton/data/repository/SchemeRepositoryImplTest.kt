package com.bodakesatish.skeleton.data.repository


import android.util.Log
import org.junit.Assert.*
import com.bodakesatish.skeleton.data.base.BaseRemoteTest
import com.bodakesatish.skeleton.data.mapper.local.SchemeMetaDataEntityLocalMapper
import com.bodakesatish.skeleton.data.mapper.local.SchemeNavDataEntityLocalMapper
import com.bodakesatish.skeleton.data.mapper.local.SearchMutualFundEntityLocalMapper
import com.bodakesatish.skeleton.data.mapper.local.UserMutualFundSummaryEntityLocalMapper
import com.bodakesatish.skeleton.data.mapper.local.UserSchemeDataEntityLocalMapper
import com.bodakesatish.skeleton.data.mapper.local.UserSchemeTransactionLocalMapper
import com.bodakesatish.skeleton.data.mapper.remote.SchemeMetaDataEntityRemoteMapper
import com.bodakesatish.skeleton.data.mapper.remote.SearchMutualFundListEntityRemoteMapper
import com.bodakesatish.skeleton.data.source.local.dao.SchemeMetaDataDao
import com.bodakesatish.skeleton.data.source.local.dao.SchemeNavDataDao
import com.bodakesatish.skeleton.data.source.local.dao.SearchMutualFundDao
import com.bodakesatish.skeleton.data.source.local.dao.UserMutualFundSummaryDao
import com.bodakesatish.skeleton.data.source.local.dao.UserSchemeTransactionDao
import com.bodakesatish.skeleton.data.source.local.dao.UserSchemesDao
import com.bodakesatish.skeleton.data.source.local.datasource.MutualFundDataSourceLocal
import com.bodakesatish.skeleton.data.source.remote.datasource.SchemeDataSourceRemote
import com.bodakesatish.skeleton.data.util.Helper
import com.bodakesatish.skeleton.data.util.enqueueResponse
import com.bodakesatish.skeleton.domain.model.request.SearchSchemeRequest
import com.bodakesatish.skeleton.domain.model.response.ResponseCode
import com.bodakesatish.skeleton.domain.repository.SchemeRepository
import com.bodakesatish.skeleton.domain.usecases.SearchMutualFundUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

class SchemeRepositoryImplTest : BaseRemoteTest() {

    private lateinit var dao: SchemeMetaDataDao
    private lateinit var remoteDataSource: SchemeRepository
    private lateinit var searchMutualFundListEntityRemoteMapper: SearchMutualFundListEntityRemoteMapper
    private lateinit var schemeMetaDataEntityRemoteMapper: SchemeMetaDataEntityRemoteMapper
    private lateinit var localDataSource: MutualFundDataSourceLocal

    private lateinit var searchMFDao: SearchMutualFundDao
    private lateinit var profileDao: UserMutualFundSummaryDao
    private lateinit var schemeNavDataDao: SchemeNavDataDao
    private lateinit var schemeMetaDataDao: SchemeMetaDataDao
    private lateinit var userSchemesDao: UserSchemesDao
    private lateinit var userSchemeTransaction: UserSchemeTransactionDao
    private lateinit var mapper: UserMutualFundSummaryEntityLocalMapper
    private lateinit var searchMfMapper: SearchMutualFundEntityLocalMapper
    private lateinit var navMapper: SchemeNavDataEntityLocalMapper
    private lateinit var schemeMetaDataEntityLocalMapper: SchemeMetaDataEntityLocalMapper
    private lateinit var userSchemeDataEntityLocalMapper: UserSchemeDataEntityLocalMapper
    private lateinit var userSchemeTransactionLocalMapper: UserSchemeTransactionLocalMapper

    private val responseDirectory = "/schemes"


    @Before
    override fun setUp() {
        super.setUp()

//        searchMFDao = db.searchMutualFundDao()
//        profileDao = db.userMutualFundSummaryDao()
//        schemeNavDataDao = db.schemeNavDataDao()
//        schemeMetaDataDao = db.schemeMetaDataDao()
//        userSchemesDao = db.userSchemesDao()
//        userSchemeTransaction = db.userSchemeTransactionDao()
//        mapper = UserMutualFundSummaryEntityLocalMapper()
//        searchMfMapper = SearchMutualFundEntityLocalMapper()
//        navMapper = SchemeNavDataEntityLocalMapper()
//        schemeMetaDataEntityLocalMapper = SchemeMetaDataEntityLocalMapper()
//        userSchemeDataEntityLocalMapper = UserSchemeDataEntityLocalMapper()
//        userSchemeTransactionLocalMapper = UserSchemeTransactionLocalMapper()

//        searchMutualFundListEntityRemoteMapper = SearchMutualFundListEntityRemoteMapper()
//        schemeMetaDataEntityRemoteMapper = SchemeMetaDataEntityRemoteMapper()
//
//        localDataSource = MutualFundDataSourceLocal(
//            searchMFDao,
//            profileDao,
//            schemeNavDataDao,
//            schemeMetaDataDao,
//            userSchemesDao,
//            userSchemeTransaction,
//            mapper,
//            searchMfMapper,
//            navMapper,
//            schemeMetaDataEntityLocalMapper,
//            userSchemeDataEntityLocalMapper,
//            userSchemeTransactionLocalMapper
//        )

//        val schemeDataSourceRemote = SchemeDataSourceRemote(
//            appService,
//            searchMutualFundListEntityRemoteMapper,
//            schemeMetaDataEntityRemoteMapper
//        )
//
//        remoteDataSource = SchemeRepositoryImpl(
//            schemeDataSourceRemote,
//            localDataSource
//        )

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getSearchMutualFundList_Success() = runTest {
        mockWebServer.enqueueResponse("/schemes.json",200)

//        val mockResponse = MockResponse()
//        val content = Helper.readFileResource("/schemes.json")
//        mockResponse.setResponseCode(200)
//        mockResponse.setBody(content)
//        mockWebServer.enqueue(mockResponse)

        val request = SearchSchemeRequest().apply {
            input = "axis"
        }
        val requestModel = SearchMutualFundUseCase.Request().apply {
            setRequestModel(request)
        }

        val response = appService.getSearchMutualFundList(request.input)
        mockWebServer.takeRequest()
//        val remoteOutput = remoteDataSource.getSearchMutualFundList(requestModel)
        Assert.assertEquals("false", response.body()!!)
    }


    @Test
    fun getSchemeMetaData() {
    }

    @Test
    fun getUserScheme() {
    }

    @Test
    fun addSchemeToUserPortfolio() {
    }

    @Test
    fun removeSchemeToUserPortfolio() {
    }

    @Test
    fun getUserSchemes() {
    }

    @Test
    fun insertUserSchemeTransaction() {
    }

    @Test
    fun getUserSchemeTransactionList() {
    }
}