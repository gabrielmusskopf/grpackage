package service

import br.com.gabrielgmusskopf.grpackage.server.CreatePackageGrpcKt
import br.com.gabrielgmusskopf.grpackage.server.CreatePackageRequest
import br.com.gabrielgmusskopf.grpackage.server.CreatePackageResponse
import domain.Package
import domain.PackageStatus
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import repository.PackageRepository
import java.time.LocalDate

class CreatePackageService(private val repository: PackageRepository) :
    CreatePackageGrpcKt.CreatePackageCoroutineImplBase() {

    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun create(request: CreatePackageRequest): CreatePackageResponse {
        println("Package creation requested")
        val pkg = Package(request.userId, request.productId, request.destination)
        val saved = repository.save(pkg)
        println("Created package ${saved.id}")

        GlobalScope.launch {
            deliverPackage(saved)
            deliveredPackage(saved)
        }

        return CreatePackageResponse.newBuilder()
            .setId(saved.id!!)
            .setUserId(saved.userId)
            .setProductId(saved.productId)
            .setDestination(saved.destination)
            .setStatus(saved.status.name)
            .setDeliveredDate(saved.deliveryDate.toString())
            .build();
    }

    private suspend fun deliverPackage(pkg: Package) {
        delay(5000L)
        pkg.status = PackageStatus.DELIVERING
        println("Package ${pkg.id} delivering")
    }

    private suspend fun deliveredPackage(pkg: Package) {
        delay(10000L)
        pkg.status = PackageStatus.DELIVERED
        println("Package ${pkg.id} delivered")
    }

}
