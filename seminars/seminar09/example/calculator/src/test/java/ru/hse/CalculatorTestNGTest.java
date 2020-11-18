package ru.hse;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.launcher.ApplicationLauncher;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * TODO: This is your test for the Calculator application. Please implement it.
 */
public class CalculatorTestNGTest {
    private FrameFixture window;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeMethod
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
    public void test()  {
        // TODO: Implement your test here.
    }

    @AfterMethod
    public void tearDown() {
        window.cleanUp();
    }

}
