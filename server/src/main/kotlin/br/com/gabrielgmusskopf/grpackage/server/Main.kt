package br.com.gabrielgmusskopf.grpackage.server

import br.com.gabrielgmusskopf.grpackage.server.infra.DatabaseSingleton

fun main() {
    DatabaseSingleton.init()

    val server = GrpackageServer()
    server.start()
    server.blockUntilShutdown()
}
