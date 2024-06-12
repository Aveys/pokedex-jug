package com.aveys.koin

import com.aveys.services.PokeApiService
import com.aveys.services.PokedexService
import com.aveys.services.PokemonService
import com.aveys.services.UserService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun moduleKoin() =
    module {
        singleOf(::PokeApiService)
        singleOf(::PokedexService)
        singleOf(::PokemonService)
        singleOf(::UserService)
    }
