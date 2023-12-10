package br.com.gabrielgmusskopf.grpackage.client.service

import br.com.gabrielgmusskopf.grpackage.client.domain.Package
import br.com.gabrielgmusskopf.grpackage.client.domain.PackageStatus
import br.com.gabrielgmusskopf.grpackage.client.service.ConsultPackageService.Data
import br.com.gabrielgmusskopf.grpackage.server.ConsultPackageRequest
import br.com.gabrielgmusskopf.grpackage.server.ConsultPackageResponse
import io.grpc.StatusException
import java.time.LocalDate

class ConsultPackageImpl(private val grpackageServerApi: GrpackageServerApi) : ConsultPackageService {

    override suspend fun consult(data: Data): Package? {
        val request = data.toRequest()
        try {
            val response = grpackageServerApi.consultPackage(request)
            return response.toDomain()
        } catch (e: StatusException) {
            println(e)
            return null
        }
    }

    private fun Data.toRequest(): ConsultPackageRequest = ConsultPackageRequest.newBuilder()
        .setId(this.userId)
        .build()

    private fun ConsultPackageResponse.toDomain(): Package = Package(
        this.id,
        this.userId,
        this.productId,
        this.destination,
        PackageStatus.valueOf(this.status),
        // FIXME
        if (this.deliveredDate != "") LocalDate.parse(this.deliveredDate) else null
    )

}
