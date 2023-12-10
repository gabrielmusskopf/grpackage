package br.com.gabrielgmusskopf.grpackage.client.service

import br.com.gabrielgmusskopf.grpackage.client.domain.Package

interface ConsultPackageService {

    suspend fun consult(data: Data): Package?

    data class Data(val id: Long)

}
