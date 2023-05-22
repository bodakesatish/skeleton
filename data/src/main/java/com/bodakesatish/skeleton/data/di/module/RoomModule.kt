package com.bodakesatish.skeleton.data.di.module

import android.app.Application
import androidx.room.Room
import com.bodakesatish.skeleton.data.source.local.dao.*
import com.bodakesatish.skeleton.data.source.local.database.MutualFundDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    private val DATABASE_NAME = "MutualFund"

    @Singleton
    @Provides
    fun provideMutualFundDatabase(application: Application): MutualFundDatabase {
        return Room.databaseBuilder(
            application,
            MutualFundDatabase::class.java, DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    @Singleton
    @Provides
    fun providesProfileDao(expenseDatabase: MutualFundDatabase): ProfileDao {
        return expenseDatabase.profileDao()
    }

    @Singleton
    @Provides
    fun providesUserMutualFundSummaryDao(expenseDatabase: MutualFundDatabase): UserMutualFundSummaryDao {
        return expenseDatabase.userMutualFundSummaryDao()
    }

    @Singleton
    @Provides
    fun providesSearchMutualFundDao(expenseDatabase: MutualFundDatabase): SearchMutualFundDao {
        return expenseDatabase.searchMutualFundDao()
    }

    @Singleton
    @Provides
    fun providesSchemeNavDataDao(expenseDatabase: MutualFundDatabase): SchemeNavDataDao {
        return expenseDatabase.schemeNavDataDao()
    }

    @Singleton
    @Provides
    fun providesSchemeMetaDataDao(expenseDatabase: MutualFundDatabase): SchemeMetaDataDao {
        return expenseDatabase.schemeMetaDataDao()
    }

    @Singleton
    @Provides
    fun providesUserSchemesDao(expenseDatabase: MutualFundDatabase): UserSchemesDao {
        return expenseDatabase.userSchemesDao()
    }

    @Singleton
    @Provides
    fun providesUserSchemeTransactionDao(expenseDatabase: MutualFundDatabase): UserSchemeTransactionDao {
        return expenseDatabase.userSchemeTransactionDao()
    }

}