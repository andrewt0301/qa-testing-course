import org.junit.Test;


public class CoverageTest {

  @Test
  public void testA() {
    Coverage.methodA(true);
    Coverage.methodA(false);
  }

  @Test
  public void testAB() {
    Coverage.methodAB(true,  false);
    Coverage.methodAB(false, true);
  }

  @Test
  public void testABC() {
    Coverage.methodABC(true,  false, false);
    Coverage.methodABC(false, true,  false);
    Coverage.methodABC(false, false, true);
  }

  @Test
  public void testOrABCD() {
    Coverage.methodOrABCD(true,  false, false, false);
    Coverage.methodOrABCD(false, true,  false, false);
    Coverage.methodOrABCD(false, false, true,  false);
    Coverage.methodOrABCD(false, false, false, true);
    Coverage.methodOrABCD(false, false, false, false);
  }

  @Test
  public void testAndABCD() {
    Coverage.methodAndABCD(true,  true,  true,  true);
    Coverage.methodAndABCD(true,  true,  true,  false);
    Coverage.methodAndABCD(true,  true,  false, false);
    Coverage.methodAndABCD(true,  false, false, true);
    Coverage.methodAndABCD(false, false, false, false);
  }

}
