package com.company.orders;

import org.assertj.swing.core.matcher.DialogMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.data.TableCell;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.DialogFixture;
import org.assertj.swing.fixture.FrameFixture;

public class OrdersJUnitTest {
    private FrameFixture window;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @Before
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
    public void testOrders() throws InterruptedException {
        // Menu Item Selection
        window.menuItemWithPath("File", "New").click();
        window.menuItemWithPath("Orders", "New order...").click();

        // Filling an order
        final DialogFixture dialog = window.dialog();
        dialog.comboBox("jcbProduct").selectItem("FamilyAlbum");
        dialog.spinner("jspQuantity").enterText("10");
        dialog.textBox("jtfName").enterText("John Doe");
        dialog.button(JButtonMatcher.withText("Ok")).click();

        // Checking table contents
        window.table().requireCellValue(
           TableCell.row(0).column(0),
          "John Doe"
        );

        // Closing the window
        /*window.close();
        final DialogFixture confirmation =
            window.dialog(DialogMatcher.withTitle("Confirmation"));
        confirmation.button(JButtonMatcher.withText("No")).click();*/
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }
}