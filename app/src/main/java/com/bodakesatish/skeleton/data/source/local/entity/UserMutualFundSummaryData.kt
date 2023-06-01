package com.bodakesatish.skeleton.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bodakesatish.skeleton.data.source.remote.entity.BaseEntity

@Entity(tableName = "table_user_mutual_fund_summary")
data class UserMutualFundSummaryData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "investment")
    val investment: String,
    @ColumnInfo(name = "currentValue")
    val currentValue: String,
    @ColumnInfo(name = "returns")
    val returns: String,
    @ColumnInfo(name = "valueDate")
    val valueDate: String,
    @ColumnInfo(name = "returnsPercentage")
    val returnsPercentage: String,
    @ColumnInfo(name = "currentPercentage")
    val currentPercentage: String,
    @ColumnInfo(name = "returnDate")
    val returnDate: String
)  : BaseEntity()