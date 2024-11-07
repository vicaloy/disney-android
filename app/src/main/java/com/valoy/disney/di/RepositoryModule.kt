package com.valoy.disney.di

import com.valoy.disney.domain.repository.CharacterRepository
import com.valoy.disney.infra.client.CharacterClient
import com.valoy.disney.infra.repository.RemoteCharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRemoteCharacterRepository(
        characterClient: CharacterClient,
    ): CharacterRepository {
        return RemoteCharacterRepository(characterClient)
    }
}