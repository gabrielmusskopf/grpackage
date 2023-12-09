import io.grpc.Grpc
import io.grpc.InsecureChannelCredentials
import java.util.concurrent.TimeUnit

suspend fun main() {
    val target = "localhost:50051";

    val channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create()).build();
    try {
        val client = Client(channel);
        val id = client.create(1, 1, "São Leopoldo")
        client.consult(1)
    } finally {
        channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }
}
