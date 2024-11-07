package com.valoy.disney.infra.dto

data class InfoDTO(
    val count: Int,
    val nextPage: String,
    val previousPage: String?,
    val totalPages: Int
)