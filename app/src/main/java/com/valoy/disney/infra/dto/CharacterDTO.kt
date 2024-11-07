package com.valoy.disney.infra.dto

import com.valoy.disney.domain.model.Characters

data class CharacterDTO(
    val __v: Int,
    val _id: Int,
    val allies: List<Any>,
    val createdAt: String,
    val enemies: List<Any>,
    val films: List<String>,
    val imageUrl: String?,
    val name: String,
    val parkAttractions: List<String>,
    val shortFilms: List<Any>,
    val sourceUrl: String,
    val tvShows: List<String>,
    val updatedAt: String,
    val url: String,
    val videoGames: List<String>,
)

fun CharacterDTO.toModel() = Characters(
    enemies = this.enemies,
    films = this.films,
    imageUrl = this.imageUrl ?: "",
    name = this.name
)