package shenry.passwordvalidator;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

public class UtilsTest {

    @Test
    public void createSet_withoutArguments() {
        Set<Character> result = Utils.createSet();

        Assert.assertEquals(0, result.size());
    }

    @Test
    public void createSet_nullArguments() {
        Set<Character> result = Utils.createSet(null);

        Assert.assertEquals(0, result.size());
    }

    @Test
    public void createSet_2arguments() {
        String values = "12";
        Character[] expected = new Character[] {'1', '2'};

        Set<Character> result = Utils.createSet(values);
        Character[] actual = result.toArray(new Character[result.size()]);
        Arrays.sort(actual);

        Assert.assertArrayEquals(expected, actual);
    }
}
