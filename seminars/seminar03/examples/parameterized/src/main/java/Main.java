public class Main {
    public static void main(final String[] args) {
        final int n = Integer.parseInt(args[0]);
        for (int i = 1; i <= n; i++) {
            System.out.println(i + ": " + Fibonacci.fibonacci(i));
        }
    }
}
