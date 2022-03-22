package com.example.hiltinject.di

import com.example.hiltinject.di.qualifier.ActivityHash
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ActivityModule {

    @ActivityHash
    @Provides
    fun provideHash (): String = hashCode().toString()
}