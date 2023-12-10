package br.com.gabrielgmusskopf.grpackage.client.plugins

import br.com.gabrielgmusskopf.grpackage.client.routes.packageRoutes
import br.com.gabrielgmusskopf.grpackage.client.routes.userRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        packageRoutes()
        userRoutes()
    }
}
