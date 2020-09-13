package com.automatedqa.testcomplete;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class OrdersDataModel
    extends DefaultTableModel {
  private JTable Table;
  public void setTable(JTable table) {
    Table = table;
    setDefaultTableHeight(Table.getPreferredSize().height);
  }

  public JTable getTable() {
    return Table;
  }

  private int DefaultTableHeight;
  public void setDefaultTableHeight(int d) {
    DefaultTableHeight = d;
  }

  public int getDefaultTableHeight() {
    return DefaultTableHeight;
  }

  private boolean isTableUpdate = true;
  void setTableUpdate(boolean value) {
    isTableUpdate = value;
    if(isTableUpdate)updateTable();
  }

  boolean getTableUpdate() {
    return isTableUpdate;
  }

  private void updateTable() {
    if(!getTableUpdate())return;
    Dimension d = new Dimension();
    d = Table.getPreferredSize();
    d.setSize(d.width, getRowCount() * Table.getRowHeight());
    if(d.getHeight() < getDefaultTableHeight())d.height = DefaultTableHeight;
    Table.setPreferredSize(d);
    Table.updateUI();
  }

  public OrdersDataModel() {
  }

  public boolean isCellEditable(int row, int col) {
    return false;
  }

  public void addColumns(Object[] columns) {
    for(int i = 0; i < columns.length; i++)
      addColumn(columns[i]);
  }

  public void setRowAt(Object[] row, int rowIndex) {
    for(int i = 0; i < getColumnCount(); i++)
      setValueAt(row[i], rowIndex, i);
  }

  public void clear() {
    int rowCount = getRowCount();
    if(rowCount == 0)return;
    setTableUpdate(false);
    for(int i = 0; i < rowCount; i++)
      removeRow(0);
    setTableUpdate(true);
  }

  public void addRow(Object[] row) {
    super.addRow(row);
    updateTable();
  }

  public void removeRow(int rowIndex) {
    super.removeRow(rowIndex);
    updateTable();
  }

}
