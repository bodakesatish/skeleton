package com.bodakesatish.skeleton.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bodakesatish.skeleton.data.source.remote.entity.BaseEntity

@Entity(tableName = "table_search_mutual_fund")
data class SearchMutualFundData(
    @PrimaryKey
    @ColumnInfo(name = "schemeCode")
    val schemeCode: Int,
    @ColumnInfo(name = "schemeName")
    val schemeName: String
) : BaseEntity()