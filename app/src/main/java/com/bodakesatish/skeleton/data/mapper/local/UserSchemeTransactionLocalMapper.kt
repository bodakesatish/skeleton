package com.bodakesatish.skeleton.data.mapper.local

import com.bodakesatish.skeleton.data.mapper.base.BaseMapper
import com.bodakesatish.skeleton.data.source.local.entity.UserSchemeTransactionData
import com.bodakesatish.skeleton.domain.model.response.UserSchemeTransaction
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSchemeTransactionLocalMapper
@Inject constructor() : BaseMapper<UserSchemeTransaction, UserSchemeTransactionData>() {

    override fun reverse(model: UserSchemeTransaction): UserSchemeTransactionData {
        return UserSchemeTransactionData(
            model.id,
            model.schemeCode,
            model.investment,
            model.units,
            model.nav,
            model.transactionDate
        )
    }

    override fun map(entity: UserSchemeTransactionData): UserSchemeTransaction {
        return UserSchemeTransaction(
            entity.id,
            entity.schemeCode,
            entity.investment,
            entity.units,
            entity.nav,
            entity.transactionDate
        )
    }
}