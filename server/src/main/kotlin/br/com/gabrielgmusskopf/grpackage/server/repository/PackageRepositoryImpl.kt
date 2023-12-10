package br.com.gabrielgmusskopf.grpackage.server.repository

import br.com.gabrielgmusskopf.grpackage.server.domain.Package
import br.com.gabrielgmusskopf.grpackage.server.domain.PackageStatus
import br.com.gabrielgmusskopf.grpackage.server.infra.DatabaseSingleton.dbQuery
import br.com.gabrielgmusskopf.grpackage.server.infra.Packages
import org.jetbrains.exposed.sql.*

class PackageRepositoryImpl : PackageRepository {

    override suspend fun getAll(): List<Package> = dbQuery {
        Packages.selectAll().map(::resultRowToPackage)
    }

    override suspend fun save(new: NewPackage): Package? = dbQuery {
        val insertStatement = Packages.insert {
            it[userId] = new.userId
            it[productId] = new.productId
            it[destination] = new.destination
            it[status] = PackageStatus.APPROVING
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToPackage)
    }

    override suspend fun update(pkg: Package) : Boolean = dbQuery {
        Packages.update({ Packages.id eq pkg.id }) {
            it[userId] = pkg.userId
            it[productId] = pkg.productId
            it[destination] = pkg.destination
            it[status] = pkg.status
            it[deliveryDate] = pkg.deliveryDate
        } > 0
    }

    override suspend fun getById(id: Long) = dbQuery {
        Packages
            .select { Packages.id eq id }
            .map(::resultRowToPackage)
            .singleOrNull()
    }

    private fun resultRowToPackage(row: ResultRow) = Package(
        row[Packages.id],
        row[Packages.userId],
        row[Packages.productId],
        row[Packages.destination],
        row[Packages.status],
        row[Packages.deliveryDate],
    )

}
