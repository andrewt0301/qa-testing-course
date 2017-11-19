package com.company.orders;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.text.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileFilter;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
class DialogResult {
  static final int DR_OK = 0;
  static final int DR_CANCEL = 1;
};

public class JfrmMain
    extends JFrame {

  public static void main(String[] args) throws InvocationTargetException, InterruptedException {
    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        JFrame frame = new JfrmMain();
        frame.setVisible(true);
      }
    });
  }

  class TBLFilter
      extends javax.swing.filechooser.FileFilter {
    public boolean accept(File f) {
      if(f.getName().endsWith(".tbl") || f.isDirectory()) {
        return true;
      }
      else {
        return false;
      }
    };

    public String getDescription() {
      return "Table (*.tbl)";
    }

  };

  class TXTFilter
      extends javax.swing.filechooser.FileFilter {
    public boolean accept(File f) {
      if(f.getName().endsWith(".txt") || f.isDirectory()) {
        return true;
      }
      else {
        return false;
      }
    };

    public String getDescription() {
      return "Text file (*.txt)";
    }

  };

  final String[] COLUMN_NAMES = {
      "Customer name", "Product",
      "Quantity", "Date", "Street",
      "City", "State", "Zip", "Card",
      "Card Nr", "Expiration Date"
  };

  final double DATE_1970 = 25570;
  final double DATE_CONVERT = 1000 * 60 * 60 * 24;

  int dlgResult = DialogResult.DR_CANCEL;

  private static boolean WorkWithBugs;

  private boolean isModified = false;
  private void setModified(boolean value) {
    isModified = value;
    if(isModified) {
      statusBar.setText("Modified");
    }
    else {
      statusBar.setText("");
    }
  }

  private boolean getModified() {
    return isModified;
  }

  static boolean getWorkWithBugs() {
    return WorkWithBugs;
  }

  public void changeOrder() {
    int rowIndex = jtblItems.getSelectedRow();
    String objects[] = new String[tableModel.getColumnCount()];
    objects[0] = jdlgOrder.jtfName.getText();
    objects[1] = jdlgOrder.jcbProduct.getSelectedItem().toString();
    objects[2] = jdlgOrder.jspQuantity.getValue().toString();
    objects[3] = jdlgOrder.jftfDate.getText();
    objects[4] = jdlgOrder.jtfStreet.getText();
    objects[5] = jdlgOrder.jtfCity.getText();
    objects[6] = jdlgOrder.jtfState.getText();
    objects[7] = jdlgOrder.jtfZip.getText();
    if(jdlgOrder.jrbExpress.isSelected()) {
      objects[8] = jdlgOrder.jrbExpress.getText();
    }
    else
    if(jdlgOrder.jrbMaster.isSelected()) {
      objects[8] = jdlgOrder.jrbMaster.getText();
    }
    else
    if(jdlgOrder.jrbVisa.isSelected()) {
      objects[8] = jdlgOrder.jrbVisa.getText();
    }
    objects[9] = jdlgOrder.jtfCardNumber.getText();
    objects[10] = jdlgOrder.jftfExpiration.getText();
    tableModel.setRowAt(objects, rowIndex);
  }

  private String FileName = "";
  private void setFileName(String value) {
    FileName = value;
    if(FileName == "") {
      setTitle("Orders - Untitled");
    }
    else {
      setTitle("Orders - " + FileName);
    }
  }

  private String getFileName() {
    return FileName;
  }

  private boolean isSave() {
    if(!getModified()) {
      return true;
    }
    String name = getFileName();
    if(name != "") {
      name = "'" + name + "'";
    }
    int res = JOptionPane.showConfirmDialog(this,
                                            "Save changes to file " + name +
                                            "?", "Confirmation",
                                            JOptionPane.YES_NO_CANCEL_OPTION);
    if(res == JOptionPane.YES_OPTION) {
      return jMenuFileSave_actionPerformed(new ActionEvent(this, 0, ""));
    }

    if(res == JOptionPane.NO_OPTION) {
      return true;
    }
    return false;
  }

  private void saveFile() {
    String str, s;
    try {
      BufferedWriter file = new BufferedWriter(new FileWriter(new File(
          getFileName())));
      for(int i = 0; i < tableModel.getRowCount(); i++) {
        str = (String)tableModel.getValueAt(i, 0); ;
        for(int k = 1; k < tableModel.getColumnCount(); k++) {
          s = (String)tableModel.getValueAt(i, k);
          if((k == 3) || (k == 10)) {
            Date date = new Date();
            try {
              date = jdlgOrder.dateFormat.parse(s);
            }
            catch(ParseException ex1) {
            }
            Double d = new Double(date.getTime() / DATE_CONVERT + DATE_1970);
            Integer n = new Integer(d.intValue());
            s = n.toString();

          }
          str += "\t" + s;

        }
        file.write(str + "\r\n");
      }
      file.close();
      setModified(false);
    }
    catch(IOException ex) {
    }
  }

  private void loadFile() {
    String str = new String(), s = new String();
    int cellIndex, tabIndex;
    tableModel.clear();
    try {
      BufferedReader file = new BufferedReader(new FileReader(getFileName()));
      tableModel.setTableUpdate(false);
      while((str = file.readLine()) != null) {
        tableModel.addRow(new Object[] {});
        cellIndex = 0;
        str += "\t";
        while((tabIndex = str.indexOf('\t')) > -1) {
          s = str.substring(0, tabIndex);
          str = str.replaceFirst(s + "\t", "");
          if((cellIndex == 3) || (cellIndex == 10)) {
            Double d = new Double(s);
            Date date = new Date();
            date.setTime((long)((d.doubleValue() - DATE_1970 + 1) *
                                DATE_CONVERT));
            s = jdlgOrder.dateFormat.format(date);
          }
          tableModel.setValueAt(s, tableModel.getRowCount() - 1, cellIndex);
          cellIndex++;
        }
      }
      tableModel.setTableUpdate(true);
      file.close();

    }
    catch(IOException ex) {
    }
    setModified(false);
  }

  	
  ImageIcon NewIcon = new ImageIcon(getClass().getClassLoader().getResource("images/New.gif"));
  ImageIcon OpenIcon = new ImageIcon(getClass().getClassLoader().getResource("images/Open.gif"));
  ImageIcon SaveIcon = new ImageIcon(getClass().getClassLoader().getResource("images/Save.gif"));
  ImageIcon NewOrderIcon = new ImageIcon(getClass().getClassLoader().getResource("images/NewOrder.gif"));
  ImageIcon EditOrderIcon = new ImageIcon(getClass().getClassLoader().getResource("images/EditOrder.gif"));
  ImageIcon DeleteOrderIcon  = new ImageIcon(getClass().getClassLoader().getResource("images/DeleteOrder.gif"));
  TBLFilter Tbl = new TBLFilter();
  TXTFilter Txt = new TXTFilter();
  JPanel contentPane;
  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenuFile = new JMenu();
  JMenuItem jMenuFileExit = new JMenuItem();
  JToolBar jToolBar = new JToolBar();
  JButton jbtnOpen = new JButton();
  JButton jbtnSave = new JButton();
  JLabel statusBar = new JLabel();
  Border border1;
  OrdersDataModel tableModel = new OrdersDataModel();
  JTable jtblItems = new JTable(tableModel);
  JScrollPane jscrItems = new JScrollPane(jtblItems);
  JButton jbtnNew = new JButton();
  JPanel jPanelItems = new JPanel();
  JMenuItem jMenuFileNew = new JMenuItem();
  JMenu jMenuOrders = new JMenu();
  JMenuItem jMenuFileOpen = new JMenuItem();
  JMenuItem jMenuFileSave = new JMenuItem();
  JMenuItem jMenuFileSaveAs = new JMenuItem();
  JMenu jMenuReport = new JMenu();
  JMenuItem jMenuOrdersNew = new JMenuItem();
  JMenuItem jMenuOrdersEdit = new JMenuItem();
  JMenuItem jMenuOrdersDelete = new JMenuItem();
  JMenuItem jMenuReportGenerate = new JMenuItem();
  GridLayout gridLayout1 = new GridLayout();
  Border border2;
  JButton jbtnSep = new JButton();
  JButton jbtnNewOrder = new JButton();
  JButton jbtnEditOrder = new JButton();
  JButton jbtnDeleteOrder = new JButton();
  JdlgOrders jdlgOrder = new JdlgOrders(this, "New Order", true);
  JCheckBoxMenuItem jMenuOrdersBugs = new JCheckBoxMenuItem();
  BorderLayout borderLayout1 = new BorderLayout();
  JFileChooser jfcChooseDialog = new JFileChooser();
  JPopupMenu jpmItems = new JPopupMenu();
  Border border3;
  JMenuItem jpmNew = new JMenuItem();
  JMenuItem jpmDelete = new JMenuItem();
  JMenuItem jpmEdit = new JMenuItem();

  //Construct the frame
  public JfrmMain() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception {
    border3 = BorderFactory.createEmptyBorder();
    jfcChooseDialog.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12));
    jfcChooseDialog.setFileFilter(Txt);
    contentPane = (JPanel)this.getContentPane();
    border1 = BorderFactory.createCompoundBorder(new EtchedBorder(EtchedBorder.
        RAISED, Color.white, new Color(165, 163, 151)),
                                                 BorderFactory.
                                                 createEmptyBorder(1, 0, 0, 0));
    border2 = BorderFactory.createCompoundBorder(BorderFactory.
                                                 createEtchedBorder(Color.white,
        new Color(178, 178, 178)), BorderFactory.createEmptyBorder(1, 0, 0, 0));
    contentPane.setLayout(borderLayout1);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.setSize(new Dimension(600, 380));
    this.setTitle("Orders - Untitled");

    statusBar.setBorder(border1);
    statusBar.setMaximumSize(new Dimension(19, 20));
    statusBar.setMinimumSize(new Dimension(19, 20));
    statusBar.setPreferredSize(new Dimension(19, 20));
    jMenuFile.setText("File");
    jMenuFileExit.setText("Exit");
    jMenuFileExit.addActionListener(new MainFrame_jMenuFileExit_ActionAdapter(this));
    jbtnOpen.setMaximumSize(new Dimension(23, 23));
    jbtnOpen.setMinimumSize(new Dimension(23, 23));
    jbtnOpen.setToolTipText("Open");
    jbtnOpen.setIcon(OpenIcon);
    jbtnOpen.addActionListener(new JfrmMain_jbtnOpen_actionAdapter(this));

    jbtnSave.setMaximumSize(new Dimension(23, 23));
    jbtnSave.setMinimumSize(new Dimension(23, 23));
    jbtnSave.setPreferredSize(new Dimension(23, 23));
    jbtnSave.setToolTipText("Save");
    jbtnSave.setIcon(SaveIcon);
    jbtnSave.addActionListener(new JfrmMain_jbtnSave_actionAdapter(this));

    jbtnNew.setMaximumSize(new Dimension(23, 23));
    jbtnNew.setMinimumSize(new Dimension(23, 23));
    jbtnNew.setPreferredSize(new Dimension(23, 23));
    jbtnNew.setToolTipText("New");
    jbtnNew.setIcon(NewIcon);
    jbtnNew.addActionListener(new JfrmMain_jbtnNew_actionAdapter(this));
    jPanelItems.setBackground(SystemColor.controlHighlight);
    jPanelItems.setLayout(gridLayout1);
    jtblItems.setBackground(Color.white);
    jtblItems.setAutoscrolls(true);
    jtblItems.setBorder(border3);
    jtblItems.setMaximumSize(new Dimension(32768, 32768));
    jtblItems.setMinimumSize(new Dimension(20, 20));
    jtblItems.setPreferredSize(new Dimension(350, 245));
    jtblItems.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    jtblItems.setIntercellSpacing(new Dimension(1, 1));
    jtblItems.setRowMargin(1);
    jtblItems.setShowHorizontalLines(false);
    jtblItems.setShowVerticalLines(false);

    jtblItems.addMouseListener(new JfrmMain_jtblItems_mouseAdapter(this));
    jtblItems.setSelectionMode(JTable.WHEN_FOCUSED);
    tableModel.addColumns(COLUMN_NAMES);
    tableModel.setTable(jtblItems);

    jMenuFileNew.setIcon(NewIcon);
    jMenuFileNew.setText("New");
    jMenuFileNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke('N',
        java.awt.event.KeyEvent.CTRL_MASK, false));
    jMenuFileNew.addActionListener(new JfrmMain_jMenuFileNew_actionAdapter(this));
    jMenuOrders.setText("Orders");

    jMenuFileOpen.setIcon(OpenIcon);
    jMenuFileOpen.setText("Open...");
    jMenuFileOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke('O',
        java.awt.event.KeyEvent.CTRL_MASK, false));
    jMenuFileOpen.addActionListener(new JfrmMain_jMenuFileOpen_actionAdapter(this));

    jMenuFileSave.setIcon(SaveIcon);
    jMenuFileSave.setText("Save");
    jMenuFileSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke('S',
        java.awt.event.KeyEvent.CTRL_MASK, false));
    jMenuFileSave.addActionListener(new JfrmMain_jMenuFileSave_actionAdapter(this));
    jMenuFileSaveAs.setText("Save as...");
    jMenuFileSaveAs.addActionListener(new
                                      JfrmMain_jMenuFileSaveAs_actionAdapter(this));
    jMenuReport.setText("Report");

    jMenuOrdersNew.setIcon(NewOrderIcon);
    jMenuOrdersNew.setText("New order...");
    jMenuOrdersNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.
        event.KeyEvent.VK_INSERT, java.awt.event.KeyEvent.CTRL_MASK, false));

    jMenuOrdersNew.addActionListener(new JfrmMain_jMenuOrdersNew_actionAdapter(this));

    jMenuOrdersEdit.setIcon(EditOrderIcon);
    jMenuOrdersEdit.setText("Edit order...");
    jMenuOrdersEdit.setAccelerator(javax.swing.KeyStroke.getKeyStroke('E',
        java.awt.event.KeyEvent.CTRL_MASK, false));
    jMenuOrdersEdit.addActionListener(new
                                      JfrmMain_jMenuOrdersEdit_actionAdapter(this));

    jMenuOrdersDelete.setIcon(DeleteOrderIcon);
    jMenuOrdersDelete.setText("Delete order");
    jMenuOrdersDelete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.
        awt.event.KeyEvent.VK_DELETE, 0, false));

    jMenuOrdersDelete.addActionListener(new
                                        JfrmMain_jMenuOrdersDelete_actionAdapter(this));
    jMenuReportGenerate.setText("Generate customer list...");
    jMenuReportGenerate.setAccelerator(javax.swing.KeyStroke.getKeyStroke('G',
        java.awt.event.KeyEvent.CTRL_MASK, false));
    jMenuReportGenerate.addActionListener(new
                                          JfrmMain_jMenuReportGenerate_actionAdapter(this));
    jbtnSep.setEnabled(false);
    jbtnSep.setMaximumSize(new Dimension(10, 21));
    jbtnSep.setMinimumSize(new Dimension(10, 21));
    jbtnSep.setPreferredSize(new Dimension(10, 21));
    jbtnSep.setToolTipText("");
    jbtnSep.setBorderPainted(false);
    jbtnSep.setText("");
    jbtnNewOrder.setMaximumSize(new Dimension(23, 23));
    jbtnNewOrder.setMinimumSize(new Dimension(23, 23));
    jbtnNewOrder.setPreferredSize(new Dimension(23, 23));
    jbtnNewOrder.setIcon(NewOrderIcon);

    jbtnNewOrder.addActionListener(new JfrmMain_jbtnNewOrder_actionAdapter(this));
    jbtnEditOrder.setMaximumSize(new Dimension(23, 23));
    jbtnEditOrder.setMinimumSize(new Dimension(23, 23));
    jbtnEditOrder.setPreferredSize(new Dimension(23, 23));
    jbtnEditOrder.setIcon(EditOrderIcon);

    jbtnEditOrder.addActionListener(new JfrmMain_jbtnEditOrder_actionAdapter(this));
    jbtnDeleteOrder.setMaximumSize(new Dimension(23, 23));
    jbtnDeleteOrder.setMinimumSize(new Dimension(23, 23));
    jbtnDeleteOrder.setPreferredSize(new Dimension(23, 23));
    jbtnDeleteOrder.setIcon(DeleteOrderIcon);

    jbtnDeleteOrder.addActionListener(new
                                      JfrmMain_jbtnDeleteOrder_actionAdapter(this));
    jMenuOrdersBugs.setText("Work with bugs");
    jMenuOrdersBugs.addActionListener(new
                                      JfrmMain_jMenuOrdersBugs_actionAdapter(this));
    jscrItems.getViewport().setBackground(Color.white);
    jscrItems.setAutoscrolls(true);
    jpmNew.setIcon(NewOrderIcon);
    jpmNew.setText("New order...");
    jpmNew.addActionListener(new JfrmMain_jpmNew_actionAdapter(this));
    jpmDelete.setIcon(DeleteOrderIcon);
    jpmDelete.setText("Delete");
    jpmDelete.addActionListener(new JfrmMain_jpmDelete_actionAdapter(this));
    jpmEdit.setIcon(EditOrderIcon);
    jpmEdit.setText("Edit order...");
    jpmEdit.addActionListener(new JfrmMain_jpmEdit_actionAdapter(this));

    jToolBar.add(jbtnNew, null);
    jToolBar.add(jbtnOpen);
    jToolBar.add(jbtnSave);
    jMenuFile.add(jMenuFileNew);
    jMenuFile.add(jMenuFileOpen);
    jMenuFile.add(jMenuFileSave);
    jMenuFile.add(jMenuFileSaveAs);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuFileExit);
    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jMenuOrders);
    jMenuBar1.add(jMenuReport);
    this.setJMenuBar(jMenuBar1);
    contentPane.add(jToolBar, BorderLayout.NORTH);
    contentPane.add(statusBar, BorderLayout.SOUTH);
    contentPane.add(jscrItems, BorderLayout.CENTER);
    jscrItems.getViewport().add(jtblItems, null);
    jscrItems.setViewportView(jtblItems);
    jMenuOrders.add(jMenuOrdersNew);
    jMenuOrders.add(jMenuOrdersEdit);
    jMenuOrders.add(jMenuOrdersBugs);
    jMenuOrders.addSeparator();
    jMenuOrders.add(jMenuOrdersDelete);
    jMenuReport.add(jMenuReportGenerate);
    jToolBar.add(jbtnSep, null);
    jToolBar.add(jbtnNewOrder, null);
    jToolBar.add(jbtnEditOrder, null);
    jToolBar.add(jbtnDeleteOrder, null);
    jpmItems.add(jpmNew);
    jpmItems.add(jpmEdit);
    jpmItems.add(jpmDelete);
  }

  public void jMenuFileExit_actionPerformed(ActionEvent e) {
    if(!isSave()) {
      return;
    }
    System.exit(0);
  }

  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if(e.getID() == WindowEvent.WINDOW_CLOSING) {
      jMenuFileExit_actionPerformed(null);
    }
  }

  void jbtnNew_actionPerformed(ActionEvent e) {
    jMenuFileNew_actionPerformed(e);
  }

  void jbtnNewOrder_actionPerformed(ActionEvent e) {
    jMenuOrdersNew_actionPerformed(new ActionEvent(this, 0, ""));
  }

  void jMenuOrdersBugs_actionPerformed(ActionEvent e) {
    WorkWithBugs = jMenuOrdersBugs.isSelected();
  }

  void jMenuOrdersNew_actionPerformed(ActionEvent e) {
    jdlgOrder.setTitle("New order");
    jdlgOrder.jtfName.setText("");
    jdlgOrder.jcbProduct.setSelectedIndex(0);
    jdlgOrder.jspQuantity.setValue(new Integer(1));
    String s = jdlgOrder.dateFormat.format(new Date());
    jdlgOrder.jftfDate.setText(s);
    jdlgOrder.jtfStreet.setText("");
    jdlgOrder.jtfCity.setText("");
    jdlgOrder.jtfState.setText("");
    jdlgOrder.jtfZip.setText("");
    jdlgOrder.jrbVisa.setSelected(true);
    jdlgOrder.jtfCardNumber.setText("");
    jdlgOrder.jftfExpiration.setText(s);
    jdlgOrder.show();
    if(dlgResult == DialogResult.DR_OK) {
      Date dateorder = new Date();
      try {
        dateorder = jdlgOrder.dateFormat.parse(jdlgOrder.jftfDate.getText());
      }
      catch(ParseException ex) {
      }
      if(dateorder.after(new Date())) {
        JOptionPane.showMessageDialog(
            null,
            "The specified date is not valid.\r\nThe date is changed to the current one.",
            "Warning",
            JOptionPane.WARNING_MESSAGE);
        jdlgOrder.jftfDate.setText(jdlgOrder.dateFormat.format(new Date()));
      }
      tableModel.addRow(new Object[] {});
      jtblItems.changeSelection(jtblItems.getRowCount() - 1, 0, false, false);
      changeOrder();
      setModified(true);
      dlgResult = DialogResult.DR_CANCEL;
    }
  }

  void jMenuOrdersEdit_actionPerformed(ActionEvent e) {
    int rowIndex = jtblItems.getSelectedRow();
    if(rowIndex > -1) {
      jdlgOrder.setTitle("Edit order");
      jdlgOrder.jtfName.setText((String)tableModel.getValueAt(rowIndex, 0));
      jdlgOrder.jcbProduct.setSelectedItem(tableModel.getValueAt(rowIndex, 1));
      jdlgOrder.jspQuantity.setValue(new Integer(tableModel.getValueAt(rowIndex,
          2).toString()));
      jdlgOrder.jftfDate.setText((String)tableModel.getValueAt(rowIndex, 3));
      jdlgOrder.jtfStreet.setText((String)tableModel.getValueAt(rowIndex, 4));
      jdlgOrder.jtfCity.setText((String)tableModel.getValueAt(rowIndex, 5));
      jdlgOrder.jtfState.setText((String)tableModel.getValueAt(rowIndex, 6));
      jdlgOrder.jtfZip.setText((String)tableModel.getValueAt(rowIndex, 7));
      if(tableModel.getValueAt(rowIndex,
                               8).toString().compareTo(jdlgOrder.jrbExpress.
          getText()) == 0) {
        jdlgOrder.jrbExpress.setSelected(true);
      }
      else if(tableModel.getValueAt(rowIndex,
                                    8).toString().compareTo(jdlgOrder.
          jrbMaster.getText()) == 0) {
        jdlgOrder.jrbMaster.setSelected(true);
      }
      else if(tableModel.getValueAt(rowIndex,
                                    8).toString().compareTo(jdlgOrder.
          jrbVisa.getText()) == 0) {
        jdlgOrder.jrbVisa.setSelected(true);
      }
      jdlgOrder.jtfCardNumber.setText((String)tableModel.getValueAt(rowIndex,
          9));
      jdlgOrder.jftfExpiration.setText((String)tableModel.getValueAt(rowIndex,
          10));
      jdlgOrder.show();
      if(dlgResult == DialogResult.DR_OK) {
        Date dateorder = new Date();
        try {
          dateorder = jdlgOrder.dateFormat.parse(jdlgOrder.jftfDate.getText());
        }
        catch(ParseException ex) {
        }
        if(dateorder.after(new Date())) {
          JOptionPane.showMessageDialog(
              null,
              "The specified date is not valid.\r\nThe date is changed to the current one.",
              "Warning",
              JOptionPane.WARNING_MESSAGE);
          jdlgOrder.jftfDate.setText(jdlgOrder.dateFormat.format(new Date()));
        }
        changeOrder();
        setModified(true);
        dlgResult = DialogResult.DR_CANCEL;
      }

    }
  }

  void jMenuOrdersDelete_actionPerformed(ActionEvent e) {
    int rowIndex;
    if((rowIndex = jtblItems.getSelectedRow()) > -1) {
      if(JOptionPane.showConfirmDialog(this, "Delete selected order?",
                                       "Confirmation",
                                       JOptionPane.YES_NO_OPTION) !=
         JOptionPane.YES_OPTION) {
        return;
      }
      tableModel.removeRow(rowIndex);
      setModified(true);
      if(tableModel.getRowCount() > rowIndex) {
        jtblItems.changeSelection(rowIndex, 0, false, false);
      }
    }

  }

  void jbtnEditOrder_actionPerformed(ActionEvent e) {
    jMenuOrdersEdit_actionPerformed(e);
  }

  void jbtnDeleteOrder_actionPerformed(ActionEvent e) {
    jMenuOrdersDelete_actionPerformed(e);
  }

  boolean jMenuFileSaveAs_actionPerformed(ActionEvent e) {
    if(jfcChooseDialog.getFileFilter() == Txt) {
      jfcChooseDialog.removeChoosableFileFilter(Txt);
      jfcChooseDialog.setFileFilter(Tbl);
    }
    if(jfcChooseDialog.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
      return false;
    }
    String parent = jfcChooseDialog.getSelectedFile().getParent();
    if(!parent.endsWith("\\")) {
      parent += "\\";
    }
    String name = jfcChooseDialog.getSelectedFile().getName();
    if(name.indexOf('.') == -1) {
      name += ".tbl";
    }
    setFileName(parent + name);
    saveFile();
    return true;
  }

  void jbtnOpen_actionPerformed(ActionEvent e) {
    jMenuFileOpen_actionPerformed(e);
  }

  void jtblItems_mouseClicked(MouseEvent e) {
    if(e.getButton() == e.BUTTON3) {
      jtblItems.changeSelection(jtblItems.rowAtPoint(e.getPoint()), 0, false, false);
      jpmItems.show(jtblItems, e.getX(), e.getY());
    }

  }

  boolean jMenuFileSave_actionPerformed(ActionEvent e) {
    if(getFileName() == "") {
      return jMenuFileSaveAs_actionPerformed(e);
    }
    else if(!getModified()) {
      return true;
    }
    saveFile();
    return true;
  }

  void jMenuFileOpen_actionPerformed(ActionEvent e) {
    if(!isSave()) {
      return;
    }
    if(jfcChooseDialog.getFileFilter() == Txt) {
      jfcChooseDialog.removeChoosableFileFilter(Txt);
      jfcChooseDialog.setFileFilter(Tbl);
    }
    if(jfcChooseDialog.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
      return;
    }
    if (!jfcChooseDialog.getSelectedFile().exists()) {
      JOptionPane.showMessageDialog(this, "File not found.\r\nPlease verify the correct file name was given.", "Orders", JOptionPane.WARNING_MESSAGE);
      return;
    }
    String parent = jfcChooseDialog.getSelectedFile().getParent();
    if(!parent.endsWith("\\")) {
      parent += "\\";
    }
    String name = jfcChooseDialog.getSelectedFile().getName();
    setFileName(parent + name);
    loadFile();
  }

  void jbtnSave_actionPerformed(ActionEvent e) {
    jMenuFileSave_actionPerformed(e);
  }

  void jMenuFileNew_actionPerformed(ActionEvent e) {
    if(!isSave()) {
      return;
    }
    tableModel.clear();
    setFileName("");
    setModified(false);
  }

  void jMenuReportGenerate_actionPerformed(ActionEvent e) {
    String str = new String();
    if(jfcChooseDialog.getFileFilter() == Tbl) {
      jfcChooseDialog.removeChoosableFileFilter(Tbl);
      jfcChooseDialog.setFileFilter(Txt);
    }
    if(jfcChooseDialog.showSaveDialog(this) == jfcChooseDialog.APPROVE_OPTION) {
      String parent = jfcChooseDialog.getSelectedFile().getParent();
      if(!parent.endsWith("\\")) {
        parent += "\\";
      }
      String name = jfcChooseDialog.getSelectedFile().getName();
      if(name.indexOf('.') == -1) {
        name += ".txt";
      }
      try {
        BufferedWriter file = new BufferedWriter(new FileWriter(parent + name));
        for(int i = 0; i < tableModel.getRowCount(); i++) {
          str = (String)tableModel.getValueAt(i, 0);
          file.write(str + "\r\n");
        }
        file.close();
      }
      catch(IOException ex) {
        str += "12";
      }
    }

  }

  void jpmDelete_actionPerformed(ActionEvent e) {
    jMenuOrdersDelete_actionPerformed(e);
  }

  void jpmEdit_actionPerformed(ActionEvent e) {
    jMenuOrdersEdit_actionPerformed(e);
  }

  void jpmNew_actionPerformed(ActionEvent e) {
    jbtnNewOrder_actionPerformed(e);
  }

}

class MainFrame_jMenuFileExit_ActionAdapter
    implements ActionListener {
  JfrmMain adaptee;

  MainFrame_jMenuFileExit_ActionAdapter(JfrmMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuFileExit_actionPerformed(e);
  }
}

class JfrmMain_jbtnNew_actionAdapter
    implements java.awt.event.ActionListener {
  JfrmMain adaptee;

  JfrmMain_jbtnNew_actionAdapter(JfrmMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jbtnNew_actionPerformed(e);
  }
}

class JfrmMain_jbtnNewOrder_actionAdapter
    implements java.awt.event.ActionListener {
  JfrmMain adaptee;

  JfrmMain_jbtnNewOrder_actionAdapter(JfrmMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jbtnNewOrder_actionPerformed(e);
  }
}

class JfrmMain_jMenuOrdersBugs_actionAdapter
    implements java.awt.event.ActionListener {
  JfrmMain adaptee;

  JfrmMain_jMenuOrdersBugs_actionAdapter(JfrmMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuOrdersBugs_actionPerformed(e);
  }
}

class JfrmMain_jMenuOrdersNew_actionAdapter
    implements java.awt.event.ActionListener {
  JfrmMain adaptee;

  JfrmMain_jMenuOrdersNew_actionAdapter(JfrmMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuOrdersNew_actionPerformed(e);
  }
}

class JfrmMain_jMenuOrdersEdit_actionAdapter
    implements java.awt.event.ActionListener {
  JfrmMain adaptee;

  JfrmMain_jMenuOrdersEdit_actionAdapter(JfrmMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuOrdersEdit_actionPerformed(e);
  }
}

class JfrmMain_jMenuOrdersDelete_actionAdapter
    implements java.awt.event.ActionListener {
  JfrmMain adaptee;

  JfrmMain_jMenuOrdersDelete_actionAdapter(JfrmMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuOrdersDelete_actionPerformed(e);
  }
}

class JfrmMain_jbtnEditOrder_actionAdapter
    implements java.awt.event.ActionListener {
  JfrmMain adaptee;

  JfrmMain_jbtnEditOrder_actionAdapter(JfrmMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jbtnEditOrder_actionPerformed(e);
  }
}

class JfrmMain_jbtnDeleteOrder_actionAdapter
    implements java.awt.event.ActionListener {
  JfrmMain adaptee;

  JfrmMain_jbtnDeleteOrder_actionAdapter(JfrmMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jbtnDeleteOrder_actionPerformed(e);
  }
}

class JfrmMain_jMenuFileSaveAs_actionAdapter
    implements java.awt.event.ActionListener {
  JfrmMain adaptee;

  JfrmMain_jMenuFileSaveAs_actionAdapter(JfrmMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuFileSaveAs_actionPerformed(e);
  }
}

class JfrmMain_jbtnOpen_actionAdapter
    implements java.awt.event.ActionListener {
  JfrmMain adaptee;

  JfrmMain_jbtnOpen_actionAdapter(JfrmMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jbtnOpen_actionPerformed(e);
  }
}

class JfrmMain_jtblItems_mouseAdapter
    extends java.awt.event.MouseAdapter {
  JfrmMain adaptee;

  JfrmMain_jtblItems_mouseAdapter(JfrmMain adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jtblItems_mouseClicked(e);
  }

}

class JfrmMain_jMenuFileSave_actionAdapter
    implements java.awt.event.ActionListener {
  JfrmMain adaptee;

  JfrmMain_jMenuFileSave_actionAdapter(JfrmMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuFileSave_actionPerformed(e);
  }
}

class JfrmMain_jMenuFileOpen_actionAdapter
    implements java.awt.event.ActionListener {
  JfrmMain adaptee;

  JfrmMain_jMenuFileOpen_actionAdapter(JfrmMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuFileOpen_actionPerformed(e);
  }
}

class JfrmMain_jbtnSave_actionAdapter
    implements java.awt.event.ActionListener {
  JfrmMain adaptee;

  JfrmMain_jbtnSave_actionAdapter(JfrmMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jbtnSave_actionPerformed(e);
  }
}

class JfrmMain_jMenuFileNew_actionAdapter
    implements java.awt.event.ActionListener {
  JfrmMain adaptee;

  JfrmMain_jMenuFileNew_actionAdapter(JfrmMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuFileNew_actionPerformed(e);
  }
}

class JfrmMain_jMenuReportGenerate_actionAdapter
    implements java.awt.event.ActionListener {
  JfrmMain adaptee;

  JfrmMain_jMenuReportGenerate_actionAdapter(JfrmMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuReportGenerate_actionPerformed(e);
  }
}

class JfrmMain_jpmDelete_actionAdapter
    implements java.awt.event.ActionListener {
  JfrmMain adaptee;

  JfrmMain_jpmDelete_actionAdapter(JfrmMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jpmDelete_actionPerformed(e);
  }
}

class JfrmMain_jpmEdit_actionAdapter
    implements java.awt.event.ActionListener {
  JfrmMain adaptee;

  JfrmMain_jpmEdit_actionAdapter(JfrmMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jpmEdit_actionPerformed(e);
  }
}

class JfrmMain_jpmNew_actionAdapter
    implements java.awt.event.ActionListener {
  JfrmMain adaptee;

  JfrmMain_jpmNew_actionAdapter(JfrmMain adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jpmNew_actionPerformed(e);
  }
}
