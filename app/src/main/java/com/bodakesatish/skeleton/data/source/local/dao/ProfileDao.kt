package com.bodakesatish.skeleton.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bodakesatish.skeleton.data.source.local.entity.ProfileData

@Dao
interface ProfileDao : BaseDao<ProfileData> {

    @Query("SELECT * from table_profile")
    suspend fun getProfile(): List<ProfileData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: ProfileData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(profileData: ProfileData)

    @Query("DELETE FROM table_profile")
    suspend fun delete()
}