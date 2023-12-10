package br.com.gabrielgmusskopf.grpackage.client.grpc

import br.com.gabrielgmusskopf.grpackage.client.service.GrpackageServerApi
import br.com.gabrielgmusskopf.grpackage.server.ConsultPackageGrpcKt.ConsultPackageCoroutineStub
import br.com.gabrielgmusskopf.grpackage.server.ConsultPackageRequest
import br.com.gabrielgmusskopf.grpackage.server.ConsultPackageResponse
import br.com.gabrielgmusskopf.grpackage.server.CreatePackageGrpcKt.CreatePackageCoroutineStub
import br.com.gabrielgmusskopf.grpackage.server.CreatePackageRequest
import br.com.gabrielgmusskopf.grpackage.server.CreatePackageResponse
import io.grpc.Grpc
import io.grpc.InsecureChannelCredentials
import io.grpc.ManagedChannel
import java.util.concurrent.TimeUnit

class GrpcClient(host: String, port: Int) : GrpackageServerApi {

    private val target: String = "$host:$port"

    override suspend fun createPackage(request: CreatePackageRequest): CreatePackageResponse = execute { channel ->
        CreatePackageCoroutineStub(channel).create(request)
    }

    override suspend fun consultPackage(request: ConsultPackageRequest): ConsultPackageResponse = execute { channel ->
        ConsultPackageCoroutineStub(channel).consultPackage(request)
    }

    private suspend fun <T> execute(execFunc: suspend (ManagedChannel) -> T): T {
        val channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create()).build()
        return try {
            execFunc.invoke(channel)
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }

}
