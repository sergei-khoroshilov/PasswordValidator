package shenry.passwordvalidator;

import java.util.HashSet;
import java.util.Set;

final class Utils {

    private Utils() {
    }

    public static Set<Character> createSet(String... multipleValues) {
        Set<Character> result = new HashSet<Character>();

        if (multipleValues != null) {
            for (String values : multipleValues) {
                if (values != null) {
                    for (int i = 0; i < values.length(); i++) {
                        result.add(values.charAt(i));
                    }
                }
            }
        }

        return result;
    }
}
