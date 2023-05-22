package com.bodakesatish.skeleton.data.mapper.local

import com.bodakesatish.skeleton.data.mapper.base.BaseMapper
import com.bodakesatish.skeleton.data.source.local.entity.ProfileData
import com.bodakesatish.skeleton.domain.model.response.Profile
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileEntityLocalMapper
@Inject constructor() : BaseMapper<Profile, ProfileData>() {

    override fun reverse(model: Profile): ProfileData? {
        return ProfileData(
            model.userId,
            model.name,
            model.picture ?: ""
        )
    }

    override fun map(entity: ProfileData): Profile {
        return Profile(
            entity.userId,
            entity.name,
            entity.picture
        )
    }
}