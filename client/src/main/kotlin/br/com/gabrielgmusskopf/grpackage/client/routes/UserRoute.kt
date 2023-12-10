package br.com.gabrielgmusskopf.grpackage.client.routes

import br.com.gabrielgmusskopf.grpackage.client.service.ConsultMyPackagesService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
    val consultMyPackages by inject<ConsultMyPackagesService>()

    route("/users") {
        get("/{id?}/packages") {
            val id = call.parameters["id"] ?: return@get call.respond(
                message = Error("Missing id", call.request.uri, HttpStatusCode.NotFound),
                status = HttpStatusCode.BadRequest
            )
            val data = ConsultMyPackagesService.Data(id.toLong())
            val packages = consultMyPackages.consult(data)
            call.respond(packages)
        }
    }
}
