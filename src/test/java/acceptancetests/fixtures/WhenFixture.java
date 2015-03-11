package acceptancetests.fixtures;

import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest;

import static javax.ws.rs.client.ClientBuilder.newClient;

public class WhenFixture {

    public static ActionUnderTest aRequestIsSentToTheApplication() {
        return (givens, captures) -> {
            captures.add("application response", newClient().target("http://localhost:9999/").request().get().readEntity(String.class));
            return captures;
        };
    }
}
