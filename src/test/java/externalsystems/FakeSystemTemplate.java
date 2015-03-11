package externalsystems;

import com.googlecode.yatspec.state.givenwhenthen.CapturedInputAndOutputs;
import com.googlecode.yatspec.state.givenwhenthen.InterestingGivens;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public abstract class FakeSystemTemplate {

    private final HttpServer server;
    protected InterestingGivens givens;
    protected CapturedInputAndOutputs captures;

    public FakeSystemTemplate(int port, String context,InterestingGivens givens, CapturedInputAndOutputs captures) throws IOException {
        this.givens = givens;
        this.captures = captures;
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
