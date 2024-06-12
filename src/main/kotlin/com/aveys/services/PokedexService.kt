package com.aveys.services

import com.aveys.dto.Pokemon

class PokedexService(
    private val pokeApiService: PokeApiService,
) {
    private val usersPokedex = mutableMapOf<String, MutableSet<Pokemon>>()

    suspend fun addPokemon(
        id: Int,
        user: String,
    ) {
        val pokemon = pokeApiService.getPokemon(id)
        usersPokedex[user]?.add(pokemon)
    }

    fun deletePokemon(
        id: Int,
        user: String,
    ) {
        usersPokedex[user]?.removeIf { it.id == id }
    }

    fun getPokedex(user: String): MutableSet<Pokemon> {
        if (!usersPokedex.containsKey(user)) {
            usersPokedex[user] = mutableSetOf()
        }
        return usersPokedex[user]!!
    }
}
