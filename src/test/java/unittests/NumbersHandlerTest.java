package unittests;

import com.sun.net.httpserver.HttpExchange;
import org.junit.Test;
import unittests.adapters.NumbersHandler;
import unittests.ports.SystemA;
import unittests.ports.SystemB;
import unittests.ports.SystemC;

import static org.mockito.Mockito.*;

public class NumbersHandlerTest {

    public static final HttpExchange SOME_REQUEST = null;
    private SystemA systemA = mock(SystemA.class);
    private SystemB systemB = mock(SystemB.class);
    private SystemC systemC = mock(SystemC.class);


    @Test
    public void shouldGatherDataFromRemoteSystems() {
     NumbersHandler numbersHandler = new NumbersHandler(systemA, systemB, systemC);
        try {
            when(systemA._123()).thenReturn("1 2 3");
            when(systemB._456()).thenReturn("4 5 6");

            numbersHandler.handle(SOME_REQUEST);

            verify(systemA, times(1))._123();
            verify(systemB, times(1))._456();
        } catch (Exception e) {};
    }
}