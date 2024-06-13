package com.aveys.features.pokemons.data

import com.aveys.features.pokemons.domain.dto.PokemonDTO
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Pokemons : IntIdTable() {
    val name = varchar("name", 255)
    val cries = varchar("cries", 1000).nullable()
    val sprite = varchar("sprite", 1000).nullable()
}

class Pokemon(
    id: EntityID<Int>,
) : IntEntity(id) {
    companion object : IntEntityClass<Pokemon>(Pokemons)

    var name by Pokemons.name
    var cries by Pokemons.cries
    var sprite by Pokemons.sprite

    fun toDto() = PokemonDTO(id.value, name, sprite, cries)
}
