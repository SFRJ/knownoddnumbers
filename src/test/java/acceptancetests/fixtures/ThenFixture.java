package acceptancetests.fixtures;

import com.googlecode.yatspec.state.givenwhenthen.StateExtractor;

public class ThenFixture {
    public static StateExtractor<String> theApplicationReturnedValue() {
        return captures -> captures.getType("application response", String.class);
    }

    public static StateExtractor<String> systemCReceivedValue() {
        return captures -> captures.getType("system C received value", String.class);
    }
}
