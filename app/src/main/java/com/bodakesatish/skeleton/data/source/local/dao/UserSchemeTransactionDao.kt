package com.bodakesatish.skeleton.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bodakesatish.skeleton.data.source.local.entity.UserSchemeTransactionData

@Dao
interface UserSchemeTransactionDao : BaseDao<UserSchemeTransactionData> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserSchemeTransactionData)

    @Query("SELECT * from table_user_scheme_transaction_data")
    suspend fun getAllUserSchemeTransactions(): List<UserSchemeTransactionData>

    @Query("SELECT * from table_user_scheme_transaction_data WHERE schemeCode=:schemeCode")
    suspend fun getUserSchemeTransactionBySchemeCode(schemeCode: Int): List<UserSchemeTransactionData>


}