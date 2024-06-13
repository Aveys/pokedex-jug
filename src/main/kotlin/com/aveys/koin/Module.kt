package com.aveys.koin

import com.aveys.features.pokemons.domain.PokeApiService
import com.aveys.features.users.domain.UserService
import com.aveys.services.PokedexService
import com.aveys.services.PokemonService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun moduleKoin() =
    module {
        singleOf(::PokeApiService)
        singleOf(::PokedexService)
        singleOf(::PokemonService)
        singleOf(::UserService)
    }
