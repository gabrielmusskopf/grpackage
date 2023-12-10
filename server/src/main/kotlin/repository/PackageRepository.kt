package repository

import domain.Package

interface PackageRepository {
    suspend fun getAll(): List<Package>
    suspend fun save(new: NewPackage): Package?
    suspend fun getById(id: Long): Package?
    suspend fun update(pkg: Package): Boolean
}

data class NewPackage(val userId: Long, val productId: Long, val destination: String)
