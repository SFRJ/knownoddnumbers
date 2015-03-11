package acceptancetests;

import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest;
import com.googlecode.yatspec.state.givenwhenthen.GivensBuilder;
import com.googlecode.yatspec.state.givenwhenthen.StateExtractor;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import externalsystems.SystemA;
import externalsystems.SystemB;
import externalsystems.SystemC;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import unittests.Application;

import static javax.ws.rs.client.ClientBuilder.newClient;
import static org.hamcrest.core.Is.is;

@RunWith(SpecRunner.class)
public class KnownOddNumbersTest extends TestState {

    private SystemA systemA;
    private SystemB systemB;
    private SystemC systemC;

    private Application application;

    @Before
    public void setUp() throws Exception {
        systemA = new SystemA(9996, "/", interestingGivens, capturedInputAndOutputs);
        systemB = new SystemB(9997, "/", interestingGivens, capturedInputAndOutputs);
        systemC = new SystemC(9998, "/", capturedInputAndOutputs);
        application = new Application(9999, "/");
    }

    @After
    public void tearDown() throws Exception {
        systemA.stopServer();
        systemB.stopServer();
        systemC.stopServer();
        application.stopApplication();
    }

    @Test
    public void shouldReceiveResultWhenARequestIsSentToTheApplication() throws Exception {
        given(systemARepliesWithNumbers("1,2,3"));
        and(systemBRepliesWithNumbers("4,5,6"));
        when(aRequestIsSentToTheApplication());
        then(theApplicationReturnedValue(), is("1,3,5"));
        then(systemCReceivedValue(),is("1,3,5"));
    }

    private GivensBuilder systemARepliesWithNumbers(String numbers) {
        return givens -> {
            givens.add("system A returns", numbers);
            return givens;
        };
    }

    private GivensBuilder systemBRepliesWithNumbers(String numbers) {
        return givens -> {
            givens.add("system B returns", numbers);
            return givens;
        };
    }

    private ActionUnderTest aRequestIsSentToTheApplication() {
        return (givens, captures) -> {
            captures.add("application response", newClient().target("http://localhost:9999/").request().get().readEntity(String.class));
            return captures;
        };
    }

    private StateExtractor<String> theApplicationReturnedValue() {
        return captures -> captures.getType("application response", String.class);
    }

    private StateExtractor<String> systemCReceivedValue() {
        return captures -> captures.getType("system C received value", String.class);
    }
}
