package br.com.gabrielgmusskopf.grpackage.client.plugins

import br.com.gabrielgmusskopf.grpackage.client.routes.packageRoutes
import br.com.gabrielgmusskopf.grpackage.client.routes.userRoutes
import br.com.gabrielgmusskopf.grpackage.client.service.GrpackageServerApi
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(server: GrpackageServerApi) {
    routing {
        packageRoutes(server)
        userRoutes(server)
    }
}
