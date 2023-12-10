package br.com.gabrielgmusskopf.grpackage.client.routes

import br.com.gabrielgmusskopf.grpackage.client.grpc.GrpcClient
import br.com.gabrielgmusskopf.grpackage.client.service.ConsultPackageImpl
import br.com.gabrielgmusskopf.grpackage.client.service.ConsultPackageService
import br.com.gabrielgmusskopf.grpackage.client.service.CreatePackageImpl
import br.com.gabrielgmusskopf.grpackage.client.service.CreatePackageService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

fun Route.packageRoutes() {
    val server = GrpcClient("localhost", 50051)
    val consultService = ConsultPackageImpl(server)
    val createService = CreatePackageImpl(server)

    route("/packages") {
        get("/{id?}") {
            val id = call.parameters["id"] ?: return@get call.respond(
                message = Error("Missing id", call.request.uri, HttpStatusCode.NotFound),
                status = HttpStatusCode.BadRequest
            )
            val pkg = consultService.consult(id.toData()) ?: return@get call.respond(
                message = Error("Package not found", call.request.uri, HttpStatusCode.BadRequest),
                status = HttpStatusCode.NotFound
            )
            call.respond(pkg)
        }
        post {
            val request = call.receive<CreatePackageRequest>()
            val pkg = createService.create(request.toData())
            call.respond(
                message = pkg,
                status = HttpStatusCode.Created
            )
        }
    }
}

@Serializable
data class CreatePackageRequest(val userId: Long, val productId: Long, val destination: String)

fun CreatePackageRequest.toData(): CreatePackageService.Data = CreatePackageService.Data(this.userId, this.productId, this.destination)

fun String.toData(): ConsultPackageService.Data = ConsultPackageService.Data(this.toLong())
