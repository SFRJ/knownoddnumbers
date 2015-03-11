package acceptancetests.fixtures;

import com.googlecode.yatspec.state.givenwhenthen.GivensBuilder;

public class GivensFixture {

    public static GivensBuilder systemARepliesWithNumbers(String numbers) {
        return givens -> {
            givens.add("system A returns", numbers);
            return givens;
        };
    }

    public static GivensBuilder systemBRepliesWithNumbers(String numbers) {
        return givens -> {
            givens.add("system B returns", numbers);
            return givens;
        };
    }
}
