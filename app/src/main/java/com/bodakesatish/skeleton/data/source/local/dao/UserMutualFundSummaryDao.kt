package com.bodakesatish.skeleton.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bodakesatish.skeleton.data.source.local.entity.UserMutualFundSummaryData

@Dao
interface UserMutualFundSummaryDao : BaseDao<UserMutualFundSummaryData> {

    @Query("SELECT * from table_user_mutual_fund_summary")
    suspend fun getUserMutualFundSummary(): List<UserMutualFundSummaryData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserMutualFundSummaryData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(profileData: UserMutualFundSummaryData)

    @Query("DELETE FROM table_user_mutual_fund_summary")
    suspend fun delete()
}