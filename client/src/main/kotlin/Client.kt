import br.com.gabrielgmusskopf.grpackage.server.CreatePackageGrpcKt.CreatePackageCoroutineStub
import br.com.gabrielgmusskopf.grpackage.server.CreatePackageRequest
import io.grpc.Channel

class Client(channel: Channel) {

    private val stub: CreatePackageCoroutineStub = CreatePackageCoroutineStub(channel)

    suspend fun create(name: String, user: String) {
        println("Trying to greet $name")
        val request = CreatePackageRequest.newBuilder().setName(name).setUser(user).build()
        val response = stub.create(request)
        println("Package ID: ${response.id}")
    }

}
