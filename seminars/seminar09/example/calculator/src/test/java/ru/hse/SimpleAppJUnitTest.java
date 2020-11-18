package ru.hse;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.finder.WindowFinder;

import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.launcher.ApplicationLauncher;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This is an example demonstrating how to create
 * a simple GUI test for a simple application in JUnit.
 */
public class SimpleAppJUnitTest {
  private FrameFixture window;

  @BeforeClass
  public static void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }

  @Before
  public void setUp() {
    final Robot robot = BasicRobot.robotWithNewAwtHierarchy();
    ApplicationLauncher.application(SimpleApp.class).start();
    window = WindowFinder.findFrame("SimpleApp").withTimeout(10000).using(robot);
    // Other way to launch the app. Does not call the 'main' method, therefore, less preferable.
    //final SimpleApp frame = GuiActionRunner.execute(() -> new SimpleApp());
    //window = new FrameFixture(frame);
    //window.show(new Dimension(260, 200));
  }

  @Test
  public void shouldCopyTextInLabelWhenClickingButton() {
    window.textBox("textToCopy").focus().enterText("Some random text");
    window.button("copyButton").click();
    window.label("copiedText").requireText("Some random text");
  }

  @After
  public void tearDown() {
    window.cleanUp();
  }

}
