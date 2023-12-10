package br.com.gabrielgmusskopf.grpackage.server.repository

import br.com.gabrielgmusskopf.grpackage.server.domain.Package

interface PackageRepository {
    suspend fun getAll(): List<Package>
    suspend fun getAllByUserId(userId: Long): List<Package>
    suspend fun getById(id: Long): Package?
    suspend fun save(new: NewPackage): Package?
    suspend fun update(pkg: Package): Boolean
}

data class NewPackage(val userId: Long, val productId: Long, val destination: String)
