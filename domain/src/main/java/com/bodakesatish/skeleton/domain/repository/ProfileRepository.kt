package com.bodakesatish.skeleton.domain.repository

import com.bodakesatish.skeleton.domain.model.response.Profile

interface ProfileRepository {
    suspend fun getProfile() : Profile
    suspend fun insertProfile(profile: Profile)
}