package repository

import domain.Package

interface PackageRepository {
    fun getAll(): List<Package>
    fun save(pkg: Package): Package
    fun getById(id: Long): Package?
}
