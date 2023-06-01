package com.bodakesatish.skeleton.data.source.remote.datasource

import com.bodakesatish.skeleton.data.source.remote.entity.MutualFundEntity
import com.bodakesatish.skeleton.data.source.remote.entity.SchemeMetaEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppService {

    @GET("mf/search")
    suspend fun getSearchMutualFundList(@Query("q") q:String): Response<List<MutualFundEntity>>

    @GET("mf/{metadataId}")
    suspend fun getSchemeMetaData(@Path("metadataId") metadataId:Int): Response<SchemeMetaEntity>

}