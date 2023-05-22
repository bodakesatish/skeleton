package com.bodakesatish.skeleton.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bodakesatish.skeleton.data.source.local.dao.*
import com.bodakesatish.skeleton.data.source.local.entity.*

@Database(
    entities = [
        ProfileData::class,
        UserMutualFundSummaryData::class,
        SearchMutualFundData::class,
        SchemeNavData::class,
        SchemeMetaData::class,
        UserSchemesData::class,
        UserSchemeTransactionData::class
    ],
     version = 1,
     exportSchema = false
)
abstract class MutualFundDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun userMutualFundSummaryDao(): UserMutualFundSummaryDao
    abstract fun searchMutualFundDao(): SearchMutualFundDao
    abstract fun schemeNavDataDao(): SchemeNavDataDao
    abstract fun schemeMetaDataDao(): SchemeMetaDataDao
    abstract fun userSchemesDao(): UserSchemesDao
    abstract fun userSchemeTransactionDao(): UserSchemeTransactionDao
}