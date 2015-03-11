package externalsystems;


import com.googlecode.yatspec.state.givenwhenthen.CapturedInputAndOutputs;
import com.googlecode.yatspec.state.givenwhenthen.InterestingGivens;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class SystemC extends FakeSystemTemplate {

    public SystemC(int port, String context, InterestingGivens interestingGivens, CapturedInputAndOutputs capturedInputAndOutputs) throws IOException {
        super(port, context, interestingGivens, capturedInputAndOutputs);
    }

    @Override
    public HttpHandler customHandler() {
        return httpExchange -> {
            Scanner scanner = new Scanner(httpExchange.getRequestBody());
            String receivedMessage = "";
            while(scanner.hasNext()) {
                receivedMessage += scanner.next();
            }
            scanner.close();
            httpExchange.sendResponseHeaders(200, 0);
            httpExchange.close();

            captures.add("system C received value", receivedMessage);
        };
    }
}
