package com.bodakesatish.skeleton.data.base

import android.content.Context
import androidx.room.Room
import com.bodakesatish.skeleton.data.source.local.database.MutualFundDatabase
import com.bodakesatish.skeleton.data.source.remote.datasource.AppService
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

open class BaseRemoteTest {

    protected val mockWebServer = MockWebServer()
    protected lateinit var appService: AppService
    protected lateinit var db: MutualFundDatabase

    @Before
    open fun setUp() {
        mockWebServer.start()
//        MockKAnnotations.init(this, relaxUnitFun = true)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .baseUrl(mockWebServer.url("").toString())
            .build()

        appService = retrofit.create(AppService::class.java)

//        val context = ApplicationProvider.getApplicationContext<Context>()

//        db = Room.inMemoryDatabaseBuilder(
//            context,
//            MutualFundDatabase::class.java
//        ).build()

    }

    @After
    fun after() {
        //Finish web server
        mockWebServer.shutdown()
       // db.close()
    }
}