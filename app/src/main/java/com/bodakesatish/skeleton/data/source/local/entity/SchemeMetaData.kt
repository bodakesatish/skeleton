package com.bodakesatish.skeleton.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bodakesatish.skeleton.data.source.remote.entity.BaseEntity

@Entity(tableName = "table_scheme_meta_data")
data class SchemeMetaData(
    @PrimaryKey
    @ColumnInfo(name = "schemeCode")
    val schemeCode: Int,
    @ColumnInfo(name = "schemeName")
    val schemeName: String,
    @ColumnInfo(name = "fundHouse")
    val fundHouse: String,
    @ColumnInfo(name = "schemeType")
    val schemeType: String,
    @ColumnInfo(name = "schemeCategory")
    val schemeCategory: String,
    @ColumnInfo(name = "schemeLastSyncedDate")
    val schemeLastSyncedDate: String
): BaseEntity()