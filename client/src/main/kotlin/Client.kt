import br.com.gabrielgmusskopf.grpackage.server.ConsultPackageGrpcKt.ConsultPackageCoroutineStub
import br.com.gabrielgmusskopf.grpackage.server.ConsultPackageRequest
import br.com.gabrielgmusskopf.grpackage.server.CreatePackageGrpcKt.CreatePackageCoroutineStub
import br.com.gabrielgmusskopf.grpackage.server.CreatePackageRequest
import io.grpc.Channel
import io.grpc.StatusException

class Client(channel: Channel) {

    private val createPackageStub: CreatePackageCoroutineStub = CreatePackageCoroutineStub(channel)
    private val consultPackageStub: ConsultPackageCoroutineStub = ConsultPackageCoroutineStub(channel)

    suspend fun create(userId: Long, productId: Long, destination: String): Long {
        println("Creating package with product $productId to user $userId")
        val request = CreatePackageRequest.newBuilder()
            .setUserId(userId)
            .setProductId(productId)
            .setDestination(destination)
            .build()

        val response = createPackageStub.create(request)
        println("Package ID: ${response.id}")
        return response.id
    }

    suspend fun consult(packageId: Long) {
        val request = ConsultPackageRequest.newBuilder()
            .setId(packageId)
            .build()

        try {
            val response = consultPackageStub.consultPackage(request)
            println("Package status: ${response.status}")
        } catch (e: StatusException) {
            println(e)
        }
    }

}
