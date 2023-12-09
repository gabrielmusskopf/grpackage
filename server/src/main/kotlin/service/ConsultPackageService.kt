package service

import br.com.gabrielgmusskopf.grpackage.server.ConsultPackageGrpc
import br.com.gabrielgmusskopf.grpackage.server.ConsultPackageRequest
import br.com.gabrielgmusskopf.grpackage.server.ConsultPackageResponse
import domain.Package
import io.grpc.Status
import io.grpc.stub.StreamObserver
import repository.PackageRepository

class ConsultPackageService(private val repository: PackageRepository) :
    ConsultPackageGrpc.ConsultPackageImplBase() {

    override fun consultPackage(request: ConsultPackageRequest?, responseObserver: StreamObserver<ConsultPackageResponse>?) {
        println("Package consultation requested")
        val id = request?.id ?: return idNotPresent(request, responseObserver)
        val pkg = repository.getById(id) ?: return notFound(request, responseObserver)

        responseObserver?.onNext(pkg.toConsultResponse())
        responseObserver?.onCompleted()
    }

    private fun idNotPresent(
        request: ConsultPackageRequest?, responseObserver: StreamObserver<ConsultPackageResponse>?
    ) {
        responseObserver?.onError(
            Status.INVALID_ARGUMENT
                .withDescription("Package ${request?.id} not present on request")
                .asRuntimeException()
        )
    }

    private fun notFound(request: ConsultPackageRequest?, responseObserver: StreamObserver<ConsultPackageResponse>?) {
        responseObserver?.onError(
            Status.NOT_FOUND
                .withDescription("Package ${request?.id} not found")
                .asRuntimeException()
        )
    }

    private fun Package.toConsultResponse(): ConsultPackageResponse = ConsultPackageResponse.newBuilder()
        .setId(this.id!!)
        .setUserId(this.userId)
        .setProductId(this.productId)
        .setDestination(this.destination)
        .setStatus(this.status.name)
        .build()

}
