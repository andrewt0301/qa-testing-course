import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FibonacciJUnitTest {
    @Parameterized.Parameters(name = "{index}: fibonacci({0}) = {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { 0, 0 },
            { 1, 1 },
            { 2, 1 },
            { 3, 2 },
            { 4, 3 },
            { 5, 5 },
            { 6, 8 },
            { 7, 13 }
        });
    }

    @Parameterized.Parameter(0)
    public int input;

    @Parameterized.Parameter(1)
    public int expected;

    @Test
    public void testFibonacci() {
        Assert.assertEquals(expected, Fibonacci.fibonacci(input));
    }
}
