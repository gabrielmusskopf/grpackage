package br.com.gabrielgmusskopf.grpackage.client.routes

import br.com.gabrielgmusskopf.grpackage.client.service.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

fun Route.packageRoutes(server: GrpackageServerApi) {
    val consultService = ConsultPackageImpl(server)
    val createService = CreatePackageImpl(server)

    route("/packages") {
        get("/{id?}") {
            val id = call.parameters["id"] ?: return@get call.respond(
                message = Error("Missing id", call.request.uri, HttpStatusCode.NotFound),
                status = HttpStatusCode.BadRequest
            )
            val data = ConsultPackageService.Data(id.toLong())
            val pkg = consultService.consult(data) ?: return@get call.respond(
                message = Error("Package not found", call.request.uri, HttpStatusCode.BadRequest),
                status = HttpStatusCode.NotFound
            )
            call.respond(pkg)
        }
        post {
            val request = call.receive<CreatePackageRequest>()
            val data = CreatePackageService.Data(request.userId, request.productId, request.destination)
            val pkg = createService.create(data)
            call.respond(
                message = pkg,
                status = HttpStatusCode.Created
            )
        }
    }
}

@Serializable
data class CreatePackageRequest(val userId: Long, val productId: Long, val destination: String)
