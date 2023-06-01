package com.bodakesatish.skeleton.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bodakesatish.skeleton.data.source.local.entity.UserSchemesData

@Dao
interface UserSchemesDao : BaseDao<UserSchemesData> {

    @Query("SELECT * from table_user_schemes where schemeCode = :input")
    suspend fun getUserSchemeBy(input: Int): UserSchemesData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserSchemesData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( data: List<UserSchemesData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(profileData: UserSchemesData)

    @Query("DELETE FROM table_user_schemes")
    suspend fun delete()

    @Query("DELETE FROM table_user_schemes where schemeCode = :schemeCode")
    suspend fun deleteScheme(schemeCode: Int)

    @Query("SELECT * from table_user_schemes")
    suspend fun getAllUserSchemes(): List<UserSchemesData>

}