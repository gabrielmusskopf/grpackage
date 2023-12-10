package br.com.gabrielgmusskopf.grpackage.client.service

import br.com.gabrielgmusskopf.grpackage.client.ConsultMyPackageResponse
import br.com.gabrielgmusskopf.grpackage.client.ConsultMyPackagesRequest
import br.com.gabrielgmusskopf.grpackage.client.domain.Package
import br.com.gabrielgmusskopf.grpackage.client.domain.PackageStatus
import br.com.gabrielgmusskopf.grpackage.client.service.ConsultMyPackagesService.Data
import br.com.gabrielgmusskopf.grpackage.client.util.DateUtils

class ConsultMyPackagesImpl(private val grpackageServerApi: GrpackageServerApi) : ConsultMyPackagesService {

    override suspend fun consult(data: Data): List<Package> {
        return grpackageServerApi.consultMyPackages(data.toRequest())
            .packagesList
            .map{ it.toDomain() }
    }

    private fun Data.toRequest(): ConsultMyPackagesRequest = ConsultMyPackagesRequest.newBuilder()
        .setUserId(this.userId)
        .build()

    private fun ConsultMyPackageResponse.toDomain(): Package = Package(
        this.id,
        this.userId,
        this.productId,
        this.destination,
        PackageStatus.valueOf(this.status),
        DateUtils.parseOrNull(this.deliveredDate)
    )

}
