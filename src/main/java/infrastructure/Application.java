package infrastructure;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class Application {

    private static EmbeddedServer server;

    public Application(int port, String context) throws IOException {
        main(new String[]{port + "", context});
    }

    public static void main(String[] args) throws IOException {
        server = new EmbeddedServer(parseInt(args[0]), args[1]);
    }

    public void stopApplication() {
        server.stop();
    }
}
