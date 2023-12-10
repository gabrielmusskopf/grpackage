package service

import br.com.gabrielgmusskopf.grpackage.server.CreatePackageGrpcKt
import br.com.gabrielgmusskopf.grpackage.server.CreatePackageRequest
import br.com.gabrielgmusskopf.grpackage.server.CreatePackageResponse
import domain.Package
import domain.PackageStatus
import io.grpc.Status
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import repository.NewPackage
import repository.PackageRepository

class CreatePackageService(private val repository: PackageRepository) :
    CreatePackageGrpcKt.CreatePackageCoroutineImplBase() {

    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun create(request: CreatePackageRequest): CreatePackageResponse {
        val saved = repository.save(request.toNewPackage()) ?: return alreadyExistsStatus()
        println("Created package ${saved.id}")

        GlobalScope.launch {
            deliverPackage(saved)
            deliveredPackage(saved)
        }

        return saved.toResponse()
    }

    private fun alreadyExistsStatus() : CreatePackageResponse = CreatePackageResponse.newBuilder()
            .setStatus(Status.ALREADY_EXISTS.description)
            .build()

    private fun Package.toResponse() : CreatePackageResponse = CreatePackageResponse.newBuilder()
        .setId(id)
        .setUserId(userId)
        .setProductId(productId)
        .setDestination(destination)
        .setStatus(status.name)
        .setDeliveredDate(deliveryDate.toString())
        .build()

    private fun CreatePackageRequest.toNewPackage() : NewPackage = NewPackage(userId, productId, destination)

    private suspend fun deliverPackage(pkg: Package) {
        delay(5000L)
        pkg.status = PackageStatus.DELIVERING
        repository.update(pkg)
        println("Package ${pkg.id} delivering")
    }

    private suspend fun deliveredPackage(pkg: Package) {
        delay(10000L)
        pkg.status = PackageStatus.DELIVERED
        repository.update(pkg)
        println("Package ${pkg.id} delivered")
    }
}
