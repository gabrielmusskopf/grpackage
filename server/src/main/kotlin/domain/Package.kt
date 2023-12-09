package domain

import java.time.LocalDate

class Package(val userId: Long, val productId: Long, val destination: String) {
    var id: Long? = null
    var status: PackageStatus = PackageStatus.APPROVING
    var deliveryDate: LocalDate? = null
}
