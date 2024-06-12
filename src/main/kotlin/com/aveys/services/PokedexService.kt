package com.aveys.services

import com.aveys.dto.PokemonDTO
import com.aveys.entity.Pokedex
import com.aveys.entity.Pokedexs
import com.aveys.exceptions.UserNotFound
import com.aveys.utils.dbQuery
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class PokedexService(
    private val pokemonService: PokemonService,
    private val userService: UserService,
) {
    suspend fun addPokemon(
        id: Int,
        username: String,
    ) {
        val user = userService.getUserByName(username) ?: throw UserNotFound("User with name $username not found")
        val pokemon = pokemonService.getPokemon(id)

        dbQuery {
            val pokedex = Pokedex.find { Pokedexs.user eq user.id }.first()
            val newSet = pokedex.pokemon.toMutableSet().also { it.add(pokemon) }
            pokedex.pokemon = SizedCollection(newSet)
        }
    }

    suspend fun deletePokemon(
        id: Int,
        username: String,
    ) {
        val user = userService.getUserByName(username) ?: throw UserNotFound("User with name $username not found")
        dbQuery {
            Pokedex.findSingleByAndUpdate(Pokedexs.user eq user.id) {
                it.pokemon = SizedCollection(it.pokemon.toMutableSet().also { set -> set.removeIf { pkmn -> pkmn.id.value == id } })
            }
        }
    }

    suspend fun getPokedex(username: String): Set<PokemonDTO> {
        val user = userService.getUserByName(username) ?: throw UserNotFound("User with name $username not found")
        return dbQuery {
            Pokedex
                .find(Pokedexs.user eq user.id)
                .first()
                .pokemon
                .map { it.toDto() }
                .toSet()
        }
    }
}
