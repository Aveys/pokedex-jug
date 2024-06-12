package com.aveys.plugins

import com.aveys.dto.UserSignInDTO
import com.aveys.services.PokeApiService
import com.aveys.services.PokedexService
import com.aveys.services.UserService
import com.aveys.utils.user
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.authenticate
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.getOrFail
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            cause.printStackTrace()
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        val pokeService by inject<PokeApiService>()
        val pokedexService by inject<PokedexService>()
        val userService by inject<UserService>()
        route("/pokemon") {
            get("/random") {
                call.respond(pokeService.getRandomPokemon())
            }
            get("/{id}") {
                val id = call.parameters.getOrFail("id").toInt()
                call.respond(pokeService.getPokemon(id))
            }
        }
        post("/signin") {
            val body = call.receive<UserSignInDTO>()
            userService.addUser(body)
        }
        authenticate("auth-session") {
            route("/pokedex") {
                get {
                    val user = call.user()
                    val pokedex = pokedexService.getPokedex(user.name)
                    call.respond(pokedex)
                }
                route("/{id}") {
                    post {
                        val user = call.user()
                        val id = call.parameters.getOrFail("id").toInt()
                        pokedexService.addPokemon(id, user.name)
                        call.respond(HttpStatusCode.OK)
                    }
                    delete {
                        val user = call.user()
                        val id = call.parameters.getOrFail("id").toInt()
                        pokedexService.deletePokemon(id, user.name)
                        call.respond(HttpStatusCode.OK)
                    }
                }
            }
        }
    }
}
