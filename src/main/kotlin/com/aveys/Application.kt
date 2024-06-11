package com.aveys

import com.aveys.plugins.*
import io.ktor.server.application.Application

fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)

fun Application.module() {
    configureSecurity()
    configureTemplating()
    configureMonitoring()
    configureRouting()
}
