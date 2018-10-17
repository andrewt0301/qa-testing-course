package com.company.orders;

import java.text.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class JdlgOrders
    extends JDialog {

  final int DISCOUNT_COUNT = 10;

  final Product[] PRODUCTS = {
      new Product("MyMoney", 100, 8),
      new Product("FamilyAlbum", 80, 15),
      new Product("ScreenSaver", 20, 10)
  };
  private JfrmMain MainFrame;
  private Product product;

  JPanel jpnMain = new JPanel();
  JLabel jlbProduct = new JLabel();
  JComboBox jcbProduct = new JComboBox();
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel jlbQuantity = new JLabel();
  JSpinner jspQuantity = new JSpinner();
  JLabel jlbPrice = new JLabel();
  JTextField jtfPrice = new JTextField();
  JLabel jlbDiscount = new JLabel();
  JTextField jtfDiscount = new JTextField();
  JPanel jpnTotal = new JPanel();
  Border border1;
  JLabel jlbTotal = new JLabel();
  JTextField jtfTotal = new JTextField();
  JPanel jpnCustomer = new JPanel();
  Border border2;
  JLabel jlbData = new JLabel();
  JLabel jlbName = new JLabel();
  Locale l;
  DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT,
      Locale.US);
  JFormattedTextField jftfDate = new JFormattedTextField(dateFormat);
  JTextField jtfName = new JTextField();
  JLabel jlbStreet = new JLabel();
  JLabel jlbState = new JLabel();
  JTextField jtfStreet = new JTextField();
  JTextField jtfState = new JTextField();
  JLabel jlbCity = new JLabel();
  JLabel jlbZip = new JLabel();
  JTextField jtfCity = new JTextField();
  JTextField jtfZip = new JTextField();
  JLabel jlbCard = new JLabel();
  JRadioButton jrbVisa = new JRadioButton();
  JRadioButton jrbMaster = new JRadioButton();
  JRadioButton jrbExpress = new JRadioButton();
  JLabel jlbCardNr = new JLabel();
  JLabel jlbExpiration = new JLabel();
  JTextField jtfCardNumber = new JTextField();
  JFormattedTextField jftfExpiration = new JFormattedTextField(dateFormat);
  ButtonGroup btgroupCards = new ButtonGroup();
  JButton jbtOk = new JButton();
  JButton jbtCancel = new JButton();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  GridBagLayout gridBagLayout3 = new GridBagLayout();

  public void showDialog() {

  }

  public JdlgOrders(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    MainFrame = (JfrmMain)frame;
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public JdlgOrders() throws Exception {
    this(null, "", false);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbInit() throws Exception {

    jpnMain.setName("jpnMain");
    jlbProduct.setName("jlbProduct");
    jcbProduct.setName("jcbProduct");
    jlbQuantity.setName("jlbQuantity");
    jspQuantity.setName("jspQuantity");
    jlbPrice.setName("jlbPrice");
    jtfPrice.setName("jtfPrice");
    jlbDiscount.setName("jlbDiscount");
    jtfDiscount.setName("jtfDiscount");
    jpnTotal.setName("jpnTotal");
    jlbTotal.setName("jlbTotal");
    jtfTotal.setName("jtfTotal");
    jpnCustomer.setName("jpnCustomer");
    jlbData.setName("jlbData");
    jlbName.setName("jlbName");
    jftfDate.setName("jftfDate");
    jtfName.setName("jtfName");
    jlbStreet.setName("jlbStreet");
    jlbState.setName("jlbState");
    jtfStreet.setName("jtfStreet");
    jtfState.setName("jtfState");
    jlbCity.setName("jlbCity");
    jlbZip.setName("jlbZip");
    jtfCity.setName("jtfCity");
    jtfZip.setName("jtfZip");
    jlbCard.setName("jlbCard");
    jrbVisa.setName("jrbVisa");
    jrbMaster.setName("jrbMaster");
    jrbExpress.setName("jrbExpress");
    jlbCardNr.setName("jlbCardNr");
    jlbExpiration.setName("jlbExpiration");
    jtfCardNumber.setName("jtfCardNumber");
    jftfExpiration.setName("jftfExpiration");
    jbtOk.setName("jbtOk");
    jbtCancel.setName("jbtCancel");

    border1 = BorderFactory.createEtchedBorder(Color.white,
                                               new Color(165, 163, 151));
    border2 = BorderFactory.createEtchedBorder(Color.white,
                                               new Color(165, 163, 151));
    jpnMain.setLayout(gridBagLayout1);
    jpnMain.setMaximumSize(new Dimension(32768, 32768));
    jpnMain.setMinimumSize(new Dimension(10, 10));
    jpnMain.setOpaque(true);
    jpnMain.setPreferredSize(new Dimension(470, 413));
    jpnMain.setRequestFocusEnabled(false);
    jpnMain.setToolTipText("");
    this.setSize(new Dimension(467, 413));
    this.setLocationRelativeTo(MainFrame);
    this.setModal(true);
    this.setResizable(false);
    this.setTitle("New Order");
    this.getContentPane().setLayout(borderLayout1);
    jlbProduct.setLabelFor(jcbProduct);
    jlbProduct.setDisplayedMnemonic('P');
    jlbProduct.setText("Product:");
    jlbQuantity.setLabelFor(jspQuantity);
    jlbQuantity.setDisplayedMnemonic('Q');
    jlbQuantity.setText("Quantity:");
    jlbPrice.setLabelFor(jtfPrice);
    jlbPrice.setDisplayedMnemonic('r');
    jlbPrice.setText(", price per unit:");
    jtfPrice.setRequestFocusEnabled(true);
    jtfPrice.setEditable(false);
    jtfPrice.setSelectionStart(2);
    jtfPrice.setText("$0");
    jlbDiscount.setLabelFor(jtfDiscount);
    jlbDiscount.setDisplayedMnemonic('d');
    jlbDiscount.setText(", discount:");
    jtfDiscount.setEditable(false);
    jtfDiscount.setText("0%");
    jpnTotal.setBorder(border1);
    jpnTotal.setLayout(gridBagLayout2);
    jlbTotal.setLabelFor(jtfTotal);
    jlbTotal.setDisplayedMnemonic('T');
    jlbTotal.setText("Total:");
    jpnCustomer.setBorder(border2);
    jpnCustomer.setLayout(gridBagLayout3);
    jlbData.setLabelFor(jftfDate);
    jlbData.setDisplayedMnemonic('D');
    jlbData.setText("Date:");
    jlbName.setLabelFor(jtfName);
    jlbName.setDisplayedMnemonic('C');
    jlbName.setText("Customer name:");
    jlbStreet.setLabelFor(jtfStreet);
    jlbStreet.setDisplayedMnemonic('S');
    jlbStreet.setText("Street:");
    jlbState.setLabelFor(jtfState);
    jlbState.setDisplayedMnemonic('a');
    jlbState.setText("State:");
    jlbCity.setText("City:");
    jlbCity.setLabelFor(jtfCity);
    jlbCity.setDisplayedMnemonic('C');
    jlbZip.setLabelFor(jtfZip);
    jlbZip.setDisplayedMnemonic('Z');
    jlbZip.setText("Zip:");
    jlbCard.setRequestFocusEnabled(true);
    jlbCard.setText("Card:");
    jrbVisa.setText("VISA");
    jrbMaster.setText("MasterCard");
    jrbExpress.setText("American Express");
    jlbCardNr.setText("Card Nr:");
    jlbCardNr.setLabelFor(jtfCardNumber);
    jlbCardNr.setDisplayedMnemonic('N');
    jlbExpiration.setText("Expiration Date:");
    jlbExpiration.setLabelFor(jftfExpiration);
    jlbExpiration.setDisplayedMnemonic('E');
    jftfExpiration.setSelectionStart(1);
    jftfExpiration.setText("0");
    jbtOk.setText("Ok");
    jbtOk.addActionListener(new JdlgOrders_jbtOk_actionAdapter(this));
    jbtCancel.setText("Cancel");
    jbtCancel.addActionListener(new JdlgOrders_jbtCancel_actionAdapter(this));
    jtfZip.setSelectionStart(0);
    jtfZip.setText("");
    jtfState.setText("");
    jtfStreet.setText("");
    jtfTotal.setToolTipText("");
    jtfTotal.setEditable(false);
    jtfTotal.setText("$0");
    jtfCity.setText("");
    jtfCardNumber.setText("");
    jcbProduct.addActionListener(new JdlgOrders_jcbProduct_actionAdapter(this));
    jspQuantity.addChangeListener(new JdlgOrders_jspQuantity_changeAdapter(this));
    jftfDate.setText("");
    jpnMain.add(jcbProduct, new GridBagConstraints(1, 0, 4, 2, 1.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
        new Insets(9, 8, 0, 34), 254, 0));
    jpnMain.add(jspQuantity, new GridBagConstraints(1, 1, 1, 2, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(13, 7, 0, 0), 33, 0));
    jpnMain.add(jlbProduct, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(9, 4, 0, 0), 0, 0));
    jpnMain.add(jtfPrice, new GridBagConstraints(3, 1, 1, 2, 1.0, 0.0
                                                 , GridBagConstraints.WEST,
                                                 GridBagConstraints.HORIZONTAL,
                                                 new Insets(14, 6, 0, 4), 46, 0));
    jpnMain.add(jtfDiscount, new GridBagConstraints(5, 1, 1, 2, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(11, 7, 0, 9), 37, 0));
    jpnTotal.add(jlbTotal, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                                                  , GridBagConstraints.WEST,
                                                  GridBagConstraints.NONE,
                                                  new Insets(6, 10, 6, 0), 0, 0));
    jpnTotal.add(jtfTotal, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
                                                  , GridBagConstraints.WEST,
                                                  GridBagConstraints.HORIZONTAL,
                                                  new Insets(6, 6, 6, 5), 39, 0));
    this.getContentPane().add(jpnMain, BorderLayout.CENTER);
    jpnCustomer.add(jlbStreet, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(17, 75, 0, 0), 0, 0));
    jpnCustomer.add(jtfState, new GridBagConstraints(1, 3, 2, 2, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(11, 8, 48, 0), 172, 0));
    jpnCustomer.add(jrbVisa, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.NONE,
        new Insets(30, 6, 0, 0), 0, 0));
    jpnCustomer.add(jrbMaster, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.NONE,
        new Insets(30, 21, 0, 27), 0, 0));
    jpnCustomer.add(jrbExpress, new GridBagConstraints(3, 4, 2, 1, 0.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.NONE,
        new Insets(31, 0, 0, 38), 0, 0));
    jpnCustomer.add(jlbZip, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(13, 12, 0, 0), 0, 0));
    jpnCustomer.add(jlbCity, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(17, 9, 0, 0), 0, 0));
    jpnCustomer.add(jtfZip, new GridBagConstraints(4, 3, 1, 2, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(12, 0, 47, 2), 101, 0));
    jpnCustomer.add(jtfCity, new GridBagConstraints(4, 2, 1, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(16, 0, 0, 2), 102, 0));
    jpnCustomer.add(jlbName, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(19, 12, 0, 0), 0, 0));
    jpnCustomer.add(jlbData, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(7, 80, 6, 0), 0, 0));
    jpnCustomer.add(jtfName, new GridBagConstraints(1, 1, 4, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(17, 8, 0, 2), 320, 0));
    jpnCustomer.add(jtfStreet, new GridBagConstraints(1, 2, 2, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(15, 8, 0, 0), 172, 0));
    jpnCustomer.add(jftfDate, new GridBagConstraints(1, 0, 2, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(7, 8, 0, 49), 123, 0));
    jpnCustomer.add(jtfCardNumber, new GridBagConstraints(1, 5, 4, 2, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(9, 8, 35, 29), 293, 0));
    jpnCustomer.add(jftfExpiration, new GridBagConstraints(1, 6, 2, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
        new Insets(11, 8, 12, 49), 116, 0));
    jpnCustomer.add(jlbExpiration, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(13, 12, 12, 0), 0, 0));
    jpnCustomer.add(jlbCardNr, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(10, 65, 0, 0), 0, 0));
    jpnCustomer.add(jlbCard, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(34, 83, 0, 0), 0, 0));
    jpnCustomer.add(jlbState, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(12, 80, 0, 0), 0, 0));
    jpnMain.add(jlbPrice, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
                                                 , GridBagConstraints.NORTHWEST,
                                                 GridBagConstraints.NONE,
                                                 new Insets(8, 4, 2, 12), 0, 0));
    jpnMain.add(jlbQuantity, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
        , GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
        new Insets(8, 4, 2, 0), 0, 0));
    jpnMain.add(jlbDiscount, new GridBagConstraints(4, 1, 1, 2, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.NONE,
        new Insets(8, 4, 2, 0), 0, 0));
    jpnMain.add(jbtCancel, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0
                                                  , GridBagConstraints.WEST,
                                                  GridBagConstraints.NONE,
                                                  new Insets(0, -5, 4, 5), 0, 0));
    jpnMain.add(jbtOk, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0
                                              , GridBagConstraints.CENTER,
                                              GridBagConstraints.NONE,
                                              new Insets(0, 33, 4, 10), 20, 0));
    jpnMain.add(jpnTotal, new GridBagConstraints(4, 3, 2, 1, 1.0, 1.0
                                                 , GridBagConstraints.CENTER,
                                                 GridBagConstraints.BOTH,
                                                 new Insets(11, 0, 0, 8), 3, 5));
    jpnMain.add(jpnCustomer, new GridBagConstraints(0, 4, 6, 1, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(13, 4, 0, 5), 2, 12));
    btgroupCards.add(jrbVisa);
    btgroupCards.add(jrbMaster);
    btgroupCards.add(jrbExpress);
    for(int i = 0; i < PRODUCTS.length; i++) {
      jcbProduct.addItem(PRODUCTS[i].Name);
    }
  }

  void jbtCancel_actionPerformed(ActionEvent e) {
    hide();
  }

  void jbtOk_actionPerformed(ActionEvent e) {
    MainFrame.dlgResult = DialogResult.DR_OK;
    hide();
  }

  void jcbProduct_actionPerformed(ActionEvent e) {
    product = PRODUCTS[Product.getIndexByName((String)jcbProduct.
                                              getSelectedItem())];
    jtfPrice.setText("$" + new Integer(product.Price).toString());
    jspQuantity_stateChanged(new ChangeEvent(this));
  }

  void jspQuantity_stateChanged(ChangeEvent e) {
    Integer quantity = (Integer)jspQuantity.getValue();
    if(quantity.intValue() < 0) {
      jspQuantity.setValue(new Integer(0));
      quantity = new Integer(0);
    }
    int total, discount = 0;
    if(quantity.intValue() >= DISCOUNT_COUNT) {
      discount = product.Discount;
    }
    jtfDiscount.setText(new Integer(discount).toString() + "%");
    if(!MainFrame.getWorkWithBugs()) {
      total = quantity.intValue() * product.Price * (100 - discount) / 100;
    }
    else {
      total = quantity.intValue() * product.Price;
    }
    jtfTotal.setText("$" + new Integer(total).toString());
  }

}

class JdlgOrders_jbtCancel_actionAdapter
    implements java.awt.event.ActionListener {
  JdlgOrders adaptee;

  JdlgOrders_jbtCancel_actionAdapter(JdlgOrders adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jbtCancel_actionPerformed(e);
  }
}

class JdlgOrders_jbtOk_actionAdapter
    implements java.awt.event.ActionListener {
  JdlgOrders adaptee;

  JdlgOrders_jbtOk_actionAdapter(JdlgOrders adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jbtOk_actionPerformed(e);
  }
}

class JdlgOrders_jcbProduct_actionAdapter
    implements java.awt.event.ActionListener {
  JdlgOrders adaptee;

  JdlgOrders_jcbProduct_actionAdapter(JdlgOrders adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jcbProduct_actionPerformed(e);
  }
}

class JdlgOrders_jspQuantity_changeAdapter
    implements javax.swing.event.ChangeListener {
  JdlgOrders adaptee;

  JdlgOrders_jspQuantity_changeAdapter(JdlgOrders adaptee) {
    this.adaptee = adaptee;
  }

  public void stateChanged(ChangeEvent e) {
    adaptee.jspQuantity_stateChanged(e);
  }
}
