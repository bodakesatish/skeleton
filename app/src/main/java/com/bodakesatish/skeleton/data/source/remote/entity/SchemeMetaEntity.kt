package com.bodakesatish.skeleton.data.source.remote.entity

data class SchemeMetaEntity(
    val data: List<DataEntity>,
    val meta: Meta,
    val status: String
):BaseEntity()

data class DataEntity(
    val date: String,
    val nav: String
):BaseEntity()

data class Meta(
    val fund_house: String,
    val scheme_category: String,
    val scheme_code: Int,
    val scheme_name: String,
    val scheme_type: String
):BaseEntity()