package com.bodakesatish.skeleton.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_search_mutual_fund_history")
data class SearchMutualFundHistoryData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "keyword")
    val keyword: String,
    @ColumnInfo(name = "hasResult")
    val hasResult: Boolean
)