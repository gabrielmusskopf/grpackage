package br.com.gabrielgmusskopf.grpackage.server.service

import br.com.gabrielgmusskopf.grpackage.server.ConsultMyPackageResponse
import br.com.gabrielgmusskopf.grpackage.server.ConsultMyPackagesGrpcKt
import br.com.gabrielgmusskopf.grpackage.server.ConsultMyPackagesRequest
import br.com.gabrielgmusskopf.grpackage.server.ConsultMyPackagesResponse
import br.com.gabrielgmusskopf.grpackage.server.repository.PackageRepository

class ConsultMyPackagesService(private val repository: PackageRepository) :
    ConsultMyPackagesGrpcKt.ConsultMyPackagesCoroutineImplBase() {

    override suspend fun consult(request: ConsultMyPackagesRequest): ConsultMyPackagesResponse {
        val packages = repository.getAllByUserId(request.userId)
            .map { p ->  ConsultMyPackageResponse.newBuilder()
                .setDeliveredDate(p.deliveryDate.toString())
                .setDestination(p.destination)
                .setProductId(p.productId)
                .setUserId(p.userId)
                .setStatus(p.status.name)
                .setId(p.id)
                .build()
            }
        return ConsultMyPackagesResponse.newBuilder()
            .addAllPackages(packages)
            .build()
    }

}
