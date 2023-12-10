import infra.DatabaseSingleton

fun main() {
    DatabaseSingleton.init()

    val server = GrpackageServer()
    server.start()
    server.blockUntilShutdown()
}
