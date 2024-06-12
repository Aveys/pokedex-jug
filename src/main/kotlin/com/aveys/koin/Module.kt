package com.aveys.koin

import com.aveys.services.PokeApiService
import com.aveys.services.PokedexService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun moduleKoin() =
    module {
        singleOf(::PokeApiService)
        singleOf(::PokedexService)
    }
