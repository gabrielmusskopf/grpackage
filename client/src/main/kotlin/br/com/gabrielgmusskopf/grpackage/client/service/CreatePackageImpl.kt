package br.com.gabrielgmusskopf.grpackage.client.service

import br.com.gabrielgmusskopf.grpackage.client.domain.Package
import br.com.gabrielgmusskopf.grpackage.client.domain.PackageStatus
import br.com.gabrielgmusskopf.grpackage.client.service.CreatePackageService.Data
import br.com.gabrielgmusskopf.grpackage.server.CreatePackageRequest
import br.com.gabrielgmusskopf.grpackage.server.CreatePackageResponse
import java.time.LocalDate

class CreatePackageImpl(private val grpackageServerApi: GrpackageServerApi) : CreatePackageService {

    override suspend fun create(data: Data): Package {
        println("Creating package with product $data")
        val request = data.toRequest()
        val response = grpackageServerApi.createPackage(request)
        return response.toDomain()
    }

    private fun Data.toRequest(): CreatePackageRequest = CreatePackageRequest.newBuilder()
        .setUserId(this.userId)
        .setProductId(this.productId)
        .setDestination(this.destination)
        .build()

    private fun CreatePackageResponse.toDomain(): Package = Package(
        this.id,
        this.userId,
        this.productId,
        this.destination,
        PackageStatus.valueOf(this.status),
        LocalDate.parse(this.deliveredDate)
    )

}
