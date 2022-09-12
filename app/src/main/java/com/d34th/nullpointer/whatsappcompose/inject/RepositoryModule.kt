package com.d34th.nullpointer.whatsappcompose.inject

import com.d34th.nullpointer.whatsappcompose.domain.phone.PhoneRepoImpl
import com.d34th.nullpointer.whatsappcompose.domain.phone.PhoneRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    @Singleton
    abstract fun phoneRepository(
        phoneRepoImpl: PhoneRepoImpl
    ): PhoneRepository
}