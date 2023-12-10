package br.com.gabrielgmusskopf.grpackage.client.util

import java.time.LocalDate

object DateUtils {

    // TODO: Configurar gRPC para serializar null como null, ao invés de String vazia ou "null"
    fun parseOrNull(date: String): LocalDate? {
        return if (date != "" && date != "null") LocalDate.parse(date) else null
    }

}


