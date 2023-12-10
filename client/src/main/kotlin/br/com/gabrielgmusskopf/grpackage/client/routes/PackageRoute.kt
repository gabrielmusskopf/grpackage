package br.com.gabrielgmusskopf.grpackage.client.routes

import br.com.gabrielgmusskopf.grpackage.client.grpc.GrpcClient
import br.com.gabrielgmusskopf.grpackage.client.service.ConsultPackageImpl
import br.com.gabrielgmusskopf.grpackage.client.service.ConsultPackageService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.packageRoutes() {
    val server = GrpcClient("localhost", 50051)
    val consultService = ConsultPackageImpl(server)

    route("/packages") {
        get("/{id?}") {
            val id = call.parameters["id"] ?: return@get call.respond(
                message = Error("Missing id", call.request.uri, HttpStatusCode.NotFound),
                status = HttpStatusCode.BadRequest
            )
            val pkg = consultService.consult(id.toConsultData()) ?: return@get call.respond(
                message = Error("Package not found", call.request.uri, HttpStatusCode.BadRequest),
                status = HttpStatusCode.NotFound
            )
            call.respond(pkg)
        }
    }
}


fun String.toConsultData(): ConsultPackageService.Data = ConsultPackageService.Data(this.toLong())
