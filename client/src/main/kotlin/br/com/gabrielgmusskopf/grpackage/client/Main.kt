package br.com.gabrielgmusskopf.grpackage.client

import br.com.gabrielgmusskopf.grpackage.client.plugins.configureRouting
import br.com.gabrielgmusskopf.grpackage.client.plugins.configureSerialization
import br.com.gabrielgmusskopf.grpackage.client.routes.configureKoin
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureKoin()
    configureRouting()
    configureSerialization()
}

