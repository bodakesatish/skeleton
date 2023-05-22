package com.bodakesatish.skeleton.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bodakesatish.skeleton.data.source.remote.entity.BaseEntity

@Entity(tableName = "table_scheme_nav_data")
data class SchemeNavData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "schemeCode")
    val schemeCode: Int,
    @ColumnInfo(name = "navDate")
    val navDate: String,
    @ColumnInfo(name = "navValue")
    val navValue: String
): BaseEntity()