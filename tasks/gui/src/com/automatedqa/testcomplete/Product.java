package com.automatedqa.testcomplete;

import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Product {
  public String Name;
  public int Price;
  public int Discount;
  private static int Count; //+1
  private int Index;
  private static Vector Names = new Vector(0);
  class ProductName {
    String Name;
    int Index;
    ProductName(String name, int index) {
      Name = name;
      Index = index;
    }
  };
  Product(String name, int price, int discount) {
    Name = name;
    Price = price;
    Discount = discount;
    Index = Count++;
    Names.addElement(new ProductName(name, Index));
  };
  public static int getIndexByName(String name) {
    for(int i = 0; i < Names.size(); i++) {
      ProductName productName = (ProductName)Names.elementAt(i);
      if(productName.Name.equals(name))return productName.Index;
    }
    ;
    return -1;
  }
}
