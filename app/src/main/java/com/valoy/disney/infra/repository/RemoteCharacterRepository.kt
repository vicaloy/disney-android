package com.valoy.disney.infra.repository

import com.valoy.disney.domain.model.Characters
import com.valoy.disney.domain.repository.CharacterRepository
import com.valoy.disney.infra.client.CharacterClient
import com.valoy.disney.infra.dto.toModel
import javax.inject.Inject

class RemoteCharacterRepository @Inject constructor(private val characterClient: CharacterClient) :
    CharacterRepository {
    override suspend fun getAll(): List<Characters> = characterClient.getAll().data.map { it.toModel() }
}