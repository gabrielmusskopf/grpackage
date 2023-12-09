import io.grpc.Server
import io.grpc.ServerBuilder

class GrpackageServer {

    private val port: Int = 50051
    private var grpcServer: Server = ServerBuilder
        .forPort(port)
        .addService(CreatePackage())
        .build()

    fun start() {
        grpcServer.start()
        println("Started server, listening on $port")
        Runtime.getRuntime().addShutdownHook(Thread {
            println("*** shutting down gRPC server since JVM is shutting down")
            stop()
            println("*** server shut down")
        })
    }

    private fun stop() {
        grpcServer.shutdown()
    }

    fun blockUntilShutdown() {
        grpcServer.awaitTermination()
    }

}
