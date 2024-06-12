package com.aveys.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonCries(
    val latest: String?,
    val legacy: String?,
)

@Serializable
data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: PokemonSprites,
    val cries: PokemonCries,
)

@Serializable
data class PokemonSprites(
    @SerialName("back_default")
    val backDefault: String?,
    @SerialName("front_default")
    val frontDefault: String?,
)
