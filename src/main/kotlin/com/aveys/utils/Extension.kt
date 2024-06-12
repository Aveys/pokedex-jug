package com.aveys.utils

import com.aveys.exceptions.UserNotConnected
import com.aveys.plugins.UserSession
import io.ktor.server.application.ApplicationCall
import io.ktor.server.sessions.get
import io.ktor.server.sessions.sessions
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

fun ApplicationCall.user() = this.sessions.get<UserSession>() ?: throw UserNotConnected("User does not have a session")

suspend fun <T> dbQuery(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
