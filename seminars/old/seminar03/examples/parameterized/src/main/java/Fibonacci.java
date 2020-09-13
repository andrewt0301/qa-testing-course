public class Fibonacci {
    public static long fibonacci(final int n) {
        return n <= 1 ? n : fibonacci(n-1) + fibonacci(n-2);
    }
}