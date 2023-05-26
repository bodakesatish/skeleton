package com.bodakesatish.skeleton.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bodakesatish.skeleton.data.source.local.entity.SchemeMetaData

@Dao
interface SchemeMetaDataDao : BaseDao<SchemeMetaData> {

    @Query("SELECT * from table_scheme_meta_data")
    suspend fun getShcemeMetaData(): List<SchemeMetaData>

    @Query("SELECT * from table_scheme_meta_data WHERE schemeCode = :schemeCode")
    suspend fun getSchemeMetaDataById(schemeCode: Int): SchemeMetaData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(schemeMetaData: SchemeMetaData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( data: List<SchemeMetaData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(schemeNavData: SchemeMetaData)

    @Query("DELETE FROM table_scheme_meta_data")
    suspend fun delete()

    @Query("SELECT * from table_scheme_meta_data where schemeCode = :schemeCode")
    suspend fun getSchemeMetaDataBySchemeCode(schemeCode: String): List<SchemeMetaData>
}