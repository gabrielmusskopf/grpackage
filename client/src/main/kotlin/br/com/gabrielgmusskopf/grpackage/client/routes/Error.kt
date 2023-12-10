package br.com.gabrielgmusskopf.grpackage.client.routes

import io.ktor.http.*
import kotlinx.serialization.Serializable

@Serializable
data class Error(val message: String, val detail: String, val statusCode: Int) {
    constructor(message: String, detail: String, statusCode: HttpStatusCode) : this(message, detail, statusCode.value)
}
