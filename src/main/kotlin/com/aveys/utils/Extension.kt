package com.aveys.utils

import com.aveys.exceptions.UserNotConnected
import com.aveys.plugins.UserSession
import io.ktor.server.application.ApplicationCall
import io.ktor.server.sessions.get
import io.ktor.server.sessions.sessions

fun ApplicationCall.user() = this.sessions.get<UserSession>() ?: throw UserNotConnected("User does not have a session")
