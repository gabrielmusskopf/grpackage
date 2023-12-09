fun main() {
    val server = GrpackageServer()
    server.start()
    server.blockUntilShutdown()
}
