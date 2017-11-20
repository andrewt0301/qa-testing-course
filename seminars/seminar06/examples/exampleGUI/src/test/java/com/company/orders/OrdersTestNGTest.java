package com.company.orders;

import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.DialogFixture;
import org.assertj.swing.fixture.FrameFixture;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrdersTestNGTest {
    private FrameFixture window;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeMethod
    public void setUp() {
        final JfrmMain frame = GuiActionRunner.execute(new GuiQuery<JfrmMain>(){
            @Override
            protected JfrmMain executeInEDT() throws Throwable {
                return new JfrmMain();
            }
        });

        window = new FrameFixture(frame);
        window.show(); // shows the frame to test
    }

    @Test
    public void testOrders() {
       window.menuItemWithPath("File", "New").click();
       window.menuItemWithPath("Orders", "New order...").click();

       final DialogFixture dialog = window.dialog();
       dialog.comboBox("jcbProduct").selectItem("FamilyAlbum");
       dialog.spinner("jspQuantity").enterText("10");
       dialog.textBox("jtfName").enterText("John Doe");
       dialog.button(JButtonMatcher.withText("Ok"));
    }

    @AfterMethod
    public void tearDown() {
        window.cleanUp();
    }
}