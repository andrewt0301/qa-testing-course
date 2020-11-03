import org.junit.Assert;
import org.junit.Test;

public class ClassToTestTest {

  @Test
  public void testThreshold() {
    Assert.assertTrue(ClassToTest.threshold(10.0));
    Assert.assertFalse(ClassToTest.threshold(9.0));
  }

}
