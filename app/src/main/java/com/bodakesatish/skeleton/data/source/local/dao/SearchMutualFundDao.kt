package com.bodakesatish.skeleton.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bodakesatish.skeleton.data.source.local.entity.SearchMutualFundData

@Dao
interface SearchMutualFundDao : BaseDao<SearchMutualFundData> {

    @Query("SELECT * from table_search_mutual_fund where schemeName LIKE '%' || :input || '%'")
    suspend fun getMutualFundList(input: String): List<SearchMutualFundData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: SearchMutualFundData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( data: List<SearchMutualFundData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(profileData: SearchMutualFundData)

    @Query("DELETE FROM table_search_mutual_fund")
    suspend fun delete()

}