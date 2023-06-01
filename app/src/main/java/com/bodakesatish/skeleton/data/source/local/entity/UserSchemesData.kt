package com.bodakesatish.skeleton.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bodakesatish.skeleton.data.source.remote.entity.BaseEntity

@Entity(tableName = "table_user_schemes")
data class UserSchemesData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "schemeCode")
    val schemeCode: Int,
    @ColumnInfo(name = "schemeName")
    val schemeName: String,
    @ColumnInfo(name = "investment")
    val investment: Double,
    @ColumnInfo(name = "currentValue")
    val currentValue: Double,
    @ColumnInfo(name = "totalUnits")
    val totalUnits: Int,
    @ColumnInfo(name = "averageNav")
    val averageNav: Double,
    @ColumnInfo(name = "returns")
    val returns: Double,
    @ColumnInfo(name = "currentDate")
    val currentDate: String
) : BaseEntity()