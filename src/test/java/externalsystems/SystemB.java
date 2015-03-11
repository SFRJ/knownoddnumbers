package externalsystems;


import com.googlecode.yatspec.state.givenwhenthen.CapturedInputAndOutputs;
import com.googlecode.yatspec.state.givenwhenthen.InterestingGivens;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class SystemB extends FakeSystemTemplate {

    public SystemB(int port, String context, InterestingGivens interestingGivens, CapturedInputAndOutputs capturedInputAndOutputs) throws IOException {
        super(port, context, interestingGivens, capturedInputAndOutputs);
    }

    @Override
    public HttpHandler customHandler() {
        return httpExchange -> {
            String response = givens.getType("system B returns", String.class);
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.close();
            httpExchange.close();

            captures.add("output from system B", response);
        };
    }
}
