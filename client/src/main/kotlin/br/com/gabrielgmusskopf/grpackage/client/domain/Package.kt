package br.com.gabrielgmusskopf.grpackage.client.domain

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
class Package(val userId: Long, val productId: Long, val destination: String) {
    var id: Long? = null
    var status: PackageStatus = PackageStatus.APPROVING

    @Contextual
    var deliveryDate: LocalDate? = null

    constructor(
        id: Long,
        userId: Long,
        productId: Long,
        destination: String,
        status: PackageStatus,
        deliveryDate: LocalDate?
    ) : this(userId, productId, destination) {
        this.id = id
        this.status = status
        this.deliveryDate = deliveryDate
    }

}
