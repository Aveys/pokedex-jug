package com.aveys.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.SessionStorageMemory
import io.ktor.server.sessions.Sessions
import io.ktor.server.sessions.cookie
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set
import io.ktor.util.getDigestFunction

data class UserSession(
    val name: String,
    val count: Int,
) : Principal

fun Application.configureSecurity() {
    install(Sessions) {
        cookie<UserSession>("user_session", SessionStorageMemory()) {
            cookie.path = "/"
            cookie.maxAgeInSeconds = 3600
        }
    }

    val digestFunction = getDigestFunction("SHA-256") { "ktor${it.length}" }
    val hashedUserTable =
        UserHashedTableAuth(
            table =
                mapOf(
                    "arthur" to digestFunction("jug"),
                ),
            digester = digestFunction,
        )
    authentication {
        session<UserSession>("auth-session") {
            validate { session ->
                if (hashedUserTable.table.containsKey(session.name)) {
                    session
                } else {
                    null
                }
            }
            challenge {
                call.respondRedirect("/login")
            }
        }

        form(name = "auth-form") {
            userParamName = "username"
            passwordParamName = "password"
            validate { credentials ->
                hashedUserTable.authenticate(credentials)
            }
            challenge {
                call.respondRedirect("/login")
            }
        }
    }
    routing {
        authenticate("auth-form") {
            post("/login") {
                val principal = call.principal<UserIdPrincipal>()!!
                call.sessions.set(UserSession(principal.name, 1))
                call.respondRedirect("/")
            }
        }
    }
}
