package br.com.gabrielgmusskopf.grpackage.client.service

import br.com.gabrielgmusskopf.grpackage.client.domain.Package
import br.com.gabrielgmusskopf.grpackage.client.domain.PackageStatus
import br.com.gabrielgmusskopf.grpackage.client.infra.GrpcClient
import br.com.gabrielgmusskopf.grpackage.client.util.DateUtils
import br.com.gabrielgmusskopf.grpackage.server.CreatePackageRequest
import br.com.gabrielgmusskopf.grpackage.server.CreatePackageResponse
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CreatePackageService : KoinComponent {

    private val grpackageServerApi: GrpcClient by inject()

    suspend fun create(data: Data): Package {
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

    private fun CreatePackageResponse.toDomain(): Package =
        Package(id, userId, productId, destination, PackageStatus.valueOf(status), DateUtils.parseOrNull(deliveredDate))

    @Serializable
    data class Data(val userId: Long, val productId: Long, val destination: String)

}
