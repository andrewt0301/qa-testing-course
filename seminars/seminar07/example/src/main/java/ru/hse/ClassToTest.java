package ru.hse;

public class ClassToTest {

  private int invocationCount = 0;

  private static final double OFFSET = 1.0;
  private final double threshold;

  public ClassToTest(double threshold) {
    this.threshold = threshold;
  }

  public boolean threshold(double value) {
    logInvocation();
    return value >= threshold + OFFSET;
  }

  private void logInvocation() {
    invocationCount++;
  }

}