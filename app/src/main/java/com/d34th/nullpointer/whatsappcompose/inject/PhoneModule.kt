package com.d34th.nullpointer.whatsappcompose.inject

import android.content.Context
import com.d34th.nullpointer.whatsappcompose.domain.phone.PhoneRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PhoneModule {

    @Singleton
    @Provides
    fun providePhoneRepository(
        @ApplicationContext context: Context
    ): PhoneRepoImpl = PhoneRepoImpl(context)
}