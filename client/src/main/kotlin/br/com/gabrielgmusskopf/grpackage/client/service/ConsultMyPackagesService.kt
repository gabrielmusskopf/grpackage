package br.com.gabrielgmusskopf.grpackage.client.service

import br.com.gabrielgmusskopf.grpackage.client.domain.Package

interface ConsultMyPackagesService {

    suspend fun consult(data: Data): List<Package>

    data class Data(val userId: Long)

}
