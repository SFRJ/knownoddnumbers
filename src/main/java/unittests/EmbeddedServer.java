package unittests;

import com.sun.net.httpserver.HttpServer;
import unittests.adapters.NumbersHandler;
import unittests.adapters.SystemAAdapter;
import unittests.adapters.SystemBAdapter;
import unittests.adapters.SystemCAdapter;

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
