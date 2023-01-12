package team.nk.kimiljeong.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.nk.kimiljeong.data.repository.remote.implement.PostRepositoryImpl
import team.nk.kimiljeong.data.repository.remote.implement.UserRepositoryImpl
import team.nk.kimiljeong.data.repository.remote.origin.PostRepository
import team.nk.kimiljeong.data.repository.remote.origin.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository

    @Singleton
    @Binds
    fun providePostRepository(
        postRepositoryImpl: PostRepositoryImpl,
    ): PostRepository
}
