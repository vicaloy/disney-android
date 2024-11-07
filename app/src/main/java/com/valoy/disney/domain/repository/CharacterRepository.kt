package com.valoy.disney.domain.repository

import com.valoy.disney.domain.model.Characters

interface CharacterRepository {
    suspend fun getAll(): List<Characters>
}