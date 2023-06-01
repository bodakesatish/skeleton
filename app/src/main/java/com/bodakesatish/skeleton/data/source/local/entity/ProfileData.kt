package com.bodakesatish.skeleton.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bodakesatish.skeleton.data.source.remote.entity.BaseEntity

@Entity(tableName = "table_profile")
class ProfileData(
    @PrimaryKey
    @ColumnInfo(name = "userId")
    val userId : Long,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "picture")
    val picture : String
) : BaseEntity()