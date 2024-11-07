package com.valoy.disney.domain.action

import com.valoy.disney.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacters @Inject constructor(private val characterRepository: CharacterRepository) {
    suspend operator fun invoke() = characterRepository.getAll()
}