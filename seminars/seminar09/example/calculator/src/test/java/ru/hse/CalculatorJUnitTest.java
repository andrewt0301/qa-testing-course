package ru.hse;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.launcher.ApplicationLauncher;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * TODO: This is your test for the Calculator application. Please implement it.
 */
public class CalculatorJUnitTest {
  private FrameFixture window;

  @BeforeClass
  public static void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }

  @Before
  public void setUp() {
    final Robot robot = BasicRobot.robotWithNewAwtHierarchy();
    ApplicationLauncher.application(Calculator.class).start();
    window = WindowFinder.findFrame(Calculator.class).withTimeout(10000).using(robot);
    //final SimpleApp frame = GuiActionRunner.execute(() -> new SimpleApp());
    //window = new FrameFixture(frame);
    //window.show(new Dimension(260, 200)); // shows the frame to test
  }

  @Test
  public void test() {
    // TODO: Implement your test here.
  }

  @After
  public void tearDown() {
    window.cleanUp();
  }

}
