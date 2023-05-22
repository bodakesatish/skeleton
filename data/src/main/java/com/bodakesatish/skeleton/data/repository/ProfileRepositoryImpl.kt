package com.bodakesatish.skeleton.data.repository

import com.bodakesatish.skeleton.data.source.base.BaseOutput
import com.bodakesatish.skeleton.data.source.local.datasource.ProfileDataSourceLocal
import com.bodakesatish.skeleton.domain.model.response.Profile
import com.bodakesatish.skeleton.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl
@Inject
constructor(
    private val localDataSource: ProfileDataSourceLocal
) : ProfileRepository {

    override suspend fun getProfile(): Profile {
        val localOutput = localDataSource.getProfile()
        if(localOutput is BaseOutput.Success) {
            return localOutput.output!!
        }else {
            return Profile(0,"Temp","Temp")
        }
    }

    override suspend fun insertProfile(profile: Profile) {
        localDataSource.insertProfile(profile)
    }
}