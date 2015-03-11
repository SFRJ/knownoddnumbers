package externalsystems;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public abstract class FakeSystemTemplate {

    private final HttpServer server;

    public FakeSystemTemplate(int port, String context) throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress(port);
        server = HttpServer.create(socketAddress,0);
        server.createContext(context, customHandler());
        server.start();
    }

    public abstract HttpHandler customHandler();

    public void stopServer() {
        server.stop(0);
    }
}
