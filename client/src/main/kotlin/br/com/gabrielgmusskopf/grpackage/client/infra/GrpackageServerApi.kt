package br.com.gabrielgmusskopf.grpackage.client.infra

import br.com.gabrielgmusskopf.grpackage.client.ConsultMyPackagesRequest
import br.com.gabrielgmusskopf.grpackage.client.ConsultMyPackagesResponse
import br.com.gabrielgmusskopf.grpackage.server.ConsultPackageRequest
import br.com.gabrielgmusskopf.grpackage.server.ConsultPackageResponse
import br.com.gabrielgmusskopf.grpackage.server.CreatePackageRequest
import br.com.gabrielgmusskopf.grpackage.server.CreatePackageResponse

interface GrpackageServerApi {
    suspend fun createPackage(request: CreatePackageRequest): CreatePackageResponse
    suspend fun consultPackage(request: ConsultPackageRequest): ConsultPackageResponse
    suspend fun consultMyPackages(request: ConsultMyPackagesRequest): ConsultMyPackagesResponse
}
