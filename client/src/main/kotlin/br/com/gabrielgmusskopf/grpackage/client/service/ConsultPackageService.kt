package br.com.gabrielgmusskopf.grpackage.client.service

import br.com.gabrielgmusskopf.grpackage.client.domain.Package
import br.com.gabrielgmusskopf.grpackage.client.domain.PackageStatus
import br.com.gabrielgmusskopf.grpackage.client.infra.GrpcClient
import br.com.gabrielgmusskopf.grpackage.client.util.DateUtils
import br.com.gabrielgmusskopf.grpackage.server.ConsultPackageRequest
import br.com.gabrielgmusskopf.grpackage.server.ConsultPackageResponse
import io.grpc.StatusException
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ConsultPackageService : KoinComponent {

    private val grpackageServerApi: GrpcClient by inject()

    suspend fun consult(data: Data): Package? {
        val request = data.toRequest()
        return try {
            val response = grpackageServerApi.consultPackage(request)
            response.toDomain()
        } catch (e: StatusException) {
            println(e)
            null
        }
    }

    private fun Data.toRequest(): ConsultPackageRequest = ConsultPackageRequest.newBuilder()
        .setId(this.id)
        .build()

    private fun ConsultPackageResponse.toDomain(): Package =
        Package(id, userId, productId, destination, PackageStatus.valueOf(status), DateUtils.parseOrNull(deliveredDate))

    data class Data(val id: Long)

}
