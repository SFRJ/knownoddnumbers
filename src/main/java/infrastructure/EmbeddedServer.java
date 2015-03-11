package infrastructure;

import com.sun.net.httpserver.HttpServer;
import adapters.NumbersHandler;
import adapters.SystemAAdapter;
import adapters.SystemBAdapter;
import adapters.SystemCAdapter;

import java.io.IOException;
import java.net.InetSocketAddress;


public class EmbeddedServer {

    private HttpServer server;

    public EmbeddedServer(int port, String context) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext(context, new NumbersHandler(new SystemAAdapter(), new SystemBAdapter(), new SystemCAdapter()));
        server.start();
    }

    public void stop() {
        server.stop(0);
    }
}
