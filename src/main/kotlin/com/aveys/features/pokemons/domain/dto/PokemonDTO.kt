package com.aveys.features.pokemons.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDTO(
    val id: Int,
    val name: String,
    val sprite: String?,
    val cries: String?,
)
