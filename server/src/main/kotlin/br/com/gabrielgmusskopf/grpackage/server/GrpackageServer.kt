package br.com.gabrielgmusskopf.grpackage.server

import br.com.gabrielgmusskopf.grpackage.server.repository.PackageRepository
import br.com.gabrielgmusskopf.grpackage.server.repository.PackageRepositoryImpl
import br.com.gabrielgmusskopf.grpackage.server.service.ConsultPackageService
import br.com.gabrielgmusskopf.grpackage.server.service.CreatePackageService
import io.grpc.Server
import io.grpc.ServerBuilder

class GrpackageServer(private val port: Int = 50051) {

    private val packageRepository: PackageRepository = PackageRepositoryImpl()
    private var grpcServer: Server = ServerBuilder
        .forPort(port)
        .addService(CreatePackageService(packageRepository))
        .addService(ConsultPackageService(packageRepository))
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
