package com.bodakesatish.skeleton.data.di.module

import com.bodakesatish.skeleton.data.repository.ProfileRepositoryImpl
import com.bodakesatish.skeleton.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileModule {

    @Binds
    internal abstract fun provideProfileRepository(profileRepository: ProfileRepositoryImpl): ProfileRepository
}