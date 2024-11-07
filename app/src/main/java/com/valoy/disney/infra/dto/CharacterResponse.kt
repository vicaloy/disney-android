package com.valoy.disney.infra.dto

data class CharacterResponse(
    val data: List<CharacterDTO>,
    val info: InfoDTO
)