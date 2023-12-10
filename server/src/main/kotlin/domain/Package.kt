package domain

import java.time.LocalDate

class Package(val id: Long, val userId: Long, val productId: Long, val destination: String) {
    var status: PackageStatus = PackageStatus.APPROVING
    var deliveryDate: LocalDate? = null

    constructor(
        id: Long,
        userId: Long,
        productId: Long,
        destination: String,
        status: PackageStatus,
        deliveryDate: LocalDate?
    ) : this(id, userId, productId, destination) {
        this.status = status
        this.deliveryDate = deliveryDate
    }

}

