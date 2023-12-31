package br.com.gabrielgmusskopf.grpackage.server.service

import br.com.gabrielgmusskopf.grpackage.server.ConsultPackageGrpcKt
import br.com.gabrielgmusskopf.grpackage.server.ConsultPackageRequest
import br.com.gabrielgmusskopf.grpackage.server.ConsultPackageResponse
import br.com.gabrielgmusskopf.grpackage.server.domain.Package
import br.com.gabrielgmusskopf.grpackage.server.repository.PackageRepository
import io.grpc.Status.NOT_FOUND
import io.grpc.StatusRuntimeException

class ConsultPackageService(private val repository: PackageRepository) :
    ConsultPackageGrpcKt.ConsultPackageCoroutineImplBase() {

    override suspend fun consultPackage(request: ConsultPackageRequest): ConsultPackageResponse {
        val pkg = repository.getById(request.id) ?: throw entityNotFound(request)
        return pkg.toConsultResponse()
    }

    private fun entityNotFound(request: ConsultPackageRequest): Throwable =
        StatusRuntimeException(NOT_FOUND.withDescription("Package ${request.id} not found"))

    private fun Package.toConsultResponse(): ConsultPackageResponse = ConsultPackageResponse.newBuilder()
        .setId(this.id)
        .setUserId(this.userId)
        .setProductId(this.productId)
        .setDestination(this.destination)
        .setStatus(this.status.name)
        .build()

}
