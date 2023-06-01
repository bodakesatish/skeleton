package com.bodakesatish.skeleton.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bodakesatish.skeleton.data.source.remote.entity.BaseEntity

@Entity(tableName = "table_user_scheme_transaction_data")
data class UserSchemeTransactionData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "schemeCode")
    val schemeCode: Int,
    @ColumnInfo(name = "investment")
    val investment: String,
    @ColumnInfo(name = "units")
    val units: String,
    @ColumnInfo(name = "nav")
    val nav: String,
    @ColumnInfo(name = "transactionDate")
    val transactionDate: String
) : BaseEntity()