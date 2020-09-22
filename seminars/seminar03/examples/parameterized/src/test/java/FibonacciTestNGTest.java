import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FibonacciTestNGTest {
    @DataProvider(name = "Fibonacci")
    public static Object[][] fibonacci() {
        return new Object[][] {
            { 0, 0 },
            { 1, 1 },
            { 2, 1 },
            { 3, 2 },
            { 4, 3 },
            { 5, 5 },
            { 6, 8 },
            { 7, 13 }
        };
    }

    @Test(dataProvider = "Fibonacci")
    public void testFibonacci(final int input, final int expected) throws Exception {
        Assert.assertEquals(Fibonacci.fibonacci(input), expected);
    }
}