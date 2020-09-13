package com.company;

import org.assertj.swing.edt.GuiQuery;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;

import java.awt.*;

public class SimpleCopyApplicationTestNGTest {
    private FrameFixture window;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeMethod
    public void setUp() {
        final SimpleCopyApplication frame = GuiActionRunner.execute(new GuiQuery<SimpleCopyApplication>() {
            @Override
            protected SimpleCopyApplication executeInEDT() throws Throwable {
            return new SimpleCopyApplication();
            }
        });

        window = new FrameFixture(frame);
        window.show(new Dimension(260, 200)); // shows the frame to test
    }

    @Test
    public void shouldCopyTextInLabelWhenClickingButton() {
        window.textBox("textToCopy").enterText("Some random text");
        window.button("copyButton").click();
        window.label("copiedText").requireText("Some random text");
    }

    @AfterMethod
    public void tearDown() {
        window.cleanUp();
    }
}