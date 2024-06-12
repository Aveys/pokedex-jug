package com.aveys.entity

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.timestamp

object Pokedexs : IntIdTable() {
    val user = reference("user", Users)
    val createdAt = timestamp("created_at")
}

class Pokedex(
    id: EntityID<Int>,
) : IntEntity(id) {
    companion object : IntEntityClass<Pokedex>(Pokedexs)

    var user by User referencedOn Pokedexs.user
    var createdAt by Pokedexs.createdAt
    var pokemon by Pokemon via PokedexPokemons
}
