package com.valoy.disney.infra.client

import com.valoy.disney.infra.dto.CharacterResponse
import retrofit2.http.GET

interface CharacterClient {
    @GET("/character")
    suspend fun getAll(): CharacterResponse
}