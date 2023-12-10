package br.com.gabrielgmusskopf.grpackage.server.infra

import br.com.gabrielgmusskopf.grpackage.server.domain.PackageStatus
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object Packages : Table() {
    val id = long("id").autoIncrement()
    val userId = long("userId")
    val productId = long("productId")
    val destination = varchar("destination", 64)
    val status = enumeration<PackageStatus>("status")
    val deliveryDate = date("deliveryDate").nullable()
}
