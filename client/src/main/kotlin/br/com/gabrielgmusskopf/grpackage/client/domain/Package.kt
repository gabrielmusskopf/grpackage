package br.com.gabrielgmusskopf.grpackage.client.domain

import java.time.LocalDate

class Package(val userId: Long, val productId: Long, val destination: String) {
    var id: Long? = null
    var status: PackageStatus = PackageStatus.APPROVING
    var deliveryDate: LocalDate? = null

    constructor(
        id: Long,
        userId: Long,
        productId: Long,
        destination: String,
        status: PackageStatus,
        deliveryDate: LocalDate
    ) : this(userId, productId, destination) {
        this.id = id
        this.status = status
        this.deliveryDate = deliveryDate
    }

}
