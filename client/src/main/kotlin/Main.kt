import io.grpc.Grpc
import io.grpc.InsecureChannelCredentials
import java.util.concurrent.TimeUnit

suspend fun main() {
    val target = "localhost:50051";

    val channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create()).build();
    try {
        val client = Client(channel);
        client.create("Coffee", "Gabriel")
    } finally {
        channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }
}
