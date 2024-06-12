package com.aveys

import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.server.testing.testApplication
import kotlin.test.Test

class ModuleTest {
    @Test
    fun testGet() =
        testApplication {
            application {
                module()
            }
            client.get("/").apply {
                TODO("Please write your test here")
            }
        }

    @Test
    fun testGetLogin() =
        testApplication {
            application {
                module()
            }
            client.get("/login").apply {
                TODO("Please write your test here")
            }
        }

    @Test
    fun testPostLogin() =
        testApplication {
            application {
                module()
            }
            client.post("/login").apply {
                TODO("Please write your test here")
            }
        }

    @Test
    fun testGetPokedex() =
        testApplication {
            application {
                module()
            }
            client.get("/pokedex").apply {
                TODO("Please write your test here")
            }
        }

    @Test
    fun testPostPokedexIdAdd() =
        testApplication {
            application {
                module()
            }
            client.post("/pokedex/{id}/add").apply {
                TODO("Please write your test here")
            }
        }

    @Test
    fun testPostPokedexIdDelete() =
        testApplication {
            application {
                module()
            }
            client.post("/pokedex/{id}/delete").apply {
                TODO("Please write your test here")
            }
        }

    @Test
    fun testGetPokemonId() =
        testApplication {
            application {
                module()
            }
            client.get("/pokemon/{id}").apply {
                TODO("Please write your test here")
            }
        }

    @Test
    fun testGetPokemonRandom() =
        testApplication {
            application {
                module()
            }
            client.get("/pokemon/random").apply {
                TODO("Please write your test here")
            }
        }
}
