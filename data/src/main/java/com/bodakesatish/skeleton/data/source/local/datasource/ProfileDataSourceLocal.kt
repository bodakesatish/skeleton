package com.bodakesatish.skeleton.data.source.local.datasource

import com.bodakesatish.skeleton.data.source.base.BaseOutput
import com.bodakesatish.skeleton.data.source.base.DataResponseCode
import com.bodakesatish.skeleton.data.source.DataSource
import com.bodakesatish.skeleton.data.mapper.local.ProfileEntityLocalMapper
import com.bodakesatish.skeleton.data.source.local.dao.ProfileDao
import com.bodakesatish.skeleton.domain.model.response.Profile
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileDataSourceLocal
@Inject
constructor(private val profileDao: ProfileDao, private val mapper: ProfileEntityLocalMapper) :
    DataSource.ProfileDataStore {

    override suspend fun getProfile(): BaseOutput<Profile> {
        val data = profileDao.getProfile()
        return if(data.isNotEmpty()) {
            BaseOutput.Success(DataResponseCode.SUCCESS, mapper.map(data[0]))
        } else {
            BaseOutput.Success(DataResponseCode.EMPTY, Profile(0,"",""))
        }
    }

    override suspend fun insertProfile(profile: Profile) {
        val data = mapper.reverse(profile)
        data?.let {
            profileDao.insert(data)
        }
    }

}