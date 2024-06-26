package com.aveys.features.pokemons.domain

import com.aveys.features.pokemons.domain.dto.PokemonAPI
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.random.Random

class PokeApiService {
    private val client =
        HttpClient(CIO) {
            install(DefaultRequest) {
                url("https://pokeapi.co/api/v2/")
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    },
                )
            }
        }

    suspend fun getRandomPokemon(): PokemonAPI {
        val id = Random.nextInt(1, 1302)
        return getPokemon(id)
    }

    suspend fun getPokemon(id: Int): PokemonAPI {
        val pokemon = client.get("pokemon/$id").body<PokemonAPI>()
        return pokemon
    }
}
