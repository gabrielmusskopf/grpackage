package br.com.gabrielgmusskopf.grpackage.client.routes

import br.com.gabrielgmusskopf.grpackage.client.infra.GrpcClient
import br.com.gabrielgmusskopf.grpackage.client.service.ConsultMyPackagesService
import br.com.gabrielgmusskopf.grpackage.client.service.ConsultPackageService
import br.com.gabrielgmusskopf.grpackage.client.service.CreatePackageService
import io.ktor.server.application.*
import org.koin.core.context.startKoin
import org.koin.dsl.module

val serviceModule = module {
    single { GrpcClient("localhost", 50051) }
    single { ConsultMyPackagesService() }
    single { ConsultPackageService() }
    single { CreatePackageService() }
}

fun Application.configureKoin() {
    startKoin {
        modules(serviceModule)
    }
}
