package br.com.gabrielgmusskopf.grpackage.client.routes

import br.com.gabrielgmusskopf.grpackage.client.service.ConsultMyPackagesImpl
import br.com.gabrielgmusskopf.grpackage.client.service.ConsultMyPackagesService
import br.com.gabrielgmusskopf.grpackage.client.service.GrpackageServerApi
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes(server: GrpackageServerApi) {
    val consultMyPackages = ConsultMyPackagesImpl(server)

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
