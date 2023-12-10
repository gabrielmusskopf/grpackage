package br.com.gabrielgmusskopf.grpackage.client.service

import br.com.gabrielgmusskopf.grpackage.client.domain.Package
import kotlinx.serialization.Serializable

interface CreatePackageService {

    suspend fun create(data: Data): Package

    @Serializable
    data class Data(val userId: Long, val productId: Long, val destination: String)

}
