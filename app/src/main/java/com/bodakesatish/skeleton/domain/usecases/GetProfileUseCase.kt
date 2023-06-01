package com.bodakesatish.skeleton.domain.usecases

import com.bodakesatish.skeleton.domain.model.response.Profile
import com.bodakesatish.skeleton.domain.repository.ProfileRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: ProfileRepository) {

    suspend fun buildUseCase(profile: Profile)  {
        return userRepository.insertProfile(profile)
    }

    suspend fun getProfile(): Profile {
        return userRepository.getProfile()
    }
}
