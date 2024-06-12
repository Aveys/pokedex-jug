package com.aveys.entity

import org.jetbrains.exposed.sql.Table

object PokedexPokemons : Table() {
    val pokedex = reference("pokedex", Pokedexs)
    val pokemon = reference("pokemon", Pokemons)
    override val primaryKey = PrimaryKey(pokemon, pokedex)
}
