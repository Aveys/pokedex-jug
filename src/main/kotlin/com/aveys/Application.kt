package com.aveys

import com.aveys.entity.Pokedex
import com.aveys.entity.PokedexPokemons
import com.aveys.entity.Pokedexs
import com.aveys.entity.Pokemons
import com.aveys.entity.User
import com.aveys.entity.Users
import com.aveys.plugins.*
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant

fun main(args: Array<String>): Unit =
    io.ktor.server.cio.EngineMain
        .main(args)

fun Application.module() {
    Database.connect(
        "jdbc:postgresql://localhost:5432/postgres",
        driver = "org.postgresql.Driver",
        user = "root",
        password = "secret",
    )
    transaction {
        SchemaUtils.createMissingTablesAndColumns(Users, Pokemons, Pokedexs, PokedexPokemons)
        val defaultUser = User.find { Users.username eq "arthur" }.firstOrNull()
        if (defaultUser == null) {
            val usercreated =
                User.new {
                    username = "arthur"
                    password = encodePassword("jug")
                }
            Pokedex.new {
                user = usercreated
                createdAt = Instant.now()
            }
        }
    }
    configureSecurity()
    configureTemplating()
    configureMonitoring()
    configureRouting()
    configureKoin()
    configureSerialization()
}
