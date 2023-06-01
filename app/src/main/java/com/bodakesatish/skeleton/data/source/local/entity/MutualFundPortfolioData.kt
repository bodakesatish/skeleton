package com.bodakesatish.skeleton.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_mutual_fund_portfolio")
data class MutualFundPortfolioData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "schemeCode")
    val schemeCode: String
)