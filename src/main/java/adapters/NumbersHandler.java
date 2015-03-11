package adapters;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ports.SystemA;
import ports.SystemB;
import ports.SystemC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;

public class NumbersHandler implements HttpHandler {

    private SystemA systemA;
    private SystemB systemB;
    private SystemC systemC;

    public NumbersHandler(SystemA systemA, SystemB systemB, SystemC systemC) {
        this.systemA = systemA;
        this.systemB = systemB;
        this.systemC = systemC;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        List<String> allNumbers = new ArrayList<>();
        allNumbers.addAll(asList(systemA._123().split(",")));
        allNumbers.addAll(asList(systemB._456().split(",")));

        String result = allNumbers.stream()
                .filter(number -> parseInt(number) % 2 != 0)
                .reduce((seed, value) -> {
                    return seed + "," + value;
                }).get();

        systemC._saveData(result);

        httpExchange.sendResponseHeaders(200, result.length());
        httpExchange.getResponseBody().write(result.getBytes());
        httpExchange.close();
    }
}
