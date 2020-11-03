package ru.hse;

import org.junit.jupiter.api.*;

public class ClassToTestTest {

  @Test
  public void testThreshold() {
    ClassToTest classToTest = new ClassToTest(10.0);

    Assertions.assertTrue(classToTest.threshold(11.0));
    Assertions.assertFalse(classToTest.threshold(10.0));
  }

}
