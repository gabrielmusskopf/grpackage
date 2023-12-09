import br.com.gabrielgmusskopf.grpackage.server.CreatePackageGrpcKt
import br.com.gabrielgmusskopf.grpackage.server.CreatePackageRequest
import br.com.gabrielgmusskopf.grpackage.server.CreatePackageResponse

class CreatePackage : CreatePackageGrpcKt.CreatePackageCoroutineImplBase() {

    override suspend fun create(request: CreatePackageRequest): CreatePackageResponse {
        return CreatePackageResponse.newBuilder().setId("1").build();
    }

}
