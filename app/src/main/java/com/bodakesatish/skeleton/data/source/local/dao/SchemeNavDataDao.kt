package com.bodakesatish.skeleton.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bodakesatish.skeleton.data.source.local.entity.SchemeNavData

@Dao
interface SchemeNavDataDao : BaseDao<SchemeNavData> {

    @Query("SELECT * from table_scheme_nav_data")
    suspend fun getMutualFundList(): List<SchemeNavData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: SchemeNavData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( data: List<SchemeNavData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(schemeNavData: SchemeNavData)

    @Query("DELETE FROM table_scheme_nav_data")
    suspend fun delete()

    @Query("DELETE FROM table_scheme_nav_data where schemeCode = :schemeCode")
    suspend fun deleteBySchemeCode(schemeCode: Int)

    @Query("SELECT * from table_scheme_nav_data  where schemeCode = :schemeCode")
    suspend fun getSchemeNavDataBySchemeCode(schemeCode: Int): List<SchemeNavData>

}