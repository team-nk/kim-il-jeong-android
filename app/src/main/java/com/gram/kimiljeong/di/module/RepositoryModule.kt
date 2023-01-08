package com.gram.kimiljeong.di.module

import com.gram.kimiljeong.data.repository.implement.UserRepositoryImpl
import com.gram.kimiljeong.data.repository.origin.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository
}
