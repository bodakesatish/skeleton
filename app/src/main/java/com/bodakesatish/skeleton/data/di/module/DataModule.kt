package com.bodakesatish.skeleton.data.di.module

import com.bodakesatish.skeleton.data.repository.ProfileRepositoryImpl
import com.bodakesatish.skeleton.data.repository.SchemeRepositoryImpl
import com.bodakesatish.skeleton.domain.repository.ProfileRepository
import com.bodakesatish.skeleton.domain.repository.SchemeRepository
import com.bodakesatish.skeleton.data.source.DataSource

import com.bodakesatish.skeleton.data.source.remote.datasource.SchemeDataSourceRemote

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
//
//@Module
//@InstallIn(SingletonComponent::class)
//abstract class DataModule {
//
//    @Binds
//    internal abstract fun provideSchemeDataSourceRemote(schemeDataSourceRemote: SchemeDataSourceRemote): DataSource.SchemeDataStore
//
//    @Binds
//    internal abstract fun provideMutualFundRepository(profileRepository: SchemeRepositoryImpl): SchemeRepository
//
//    @Binds
//    internal abstract fun provideProfileRepository(profileRepository: ProfileRepositoryImpl): ProfileRepository
//
//}