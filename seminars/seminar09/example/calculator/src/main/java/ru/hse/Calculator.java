package ru.hse;

/*
  Java program to create a simple calculator
  with basic +, -, /, * using java swing elements

  Code is based on this tutorial:
  https://www.geeksforgeeks.org/java-swing-simple-calculator/
*/

import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {
  // Global text field.
  private final JTextField l;

  // Store operator and operands.
  private String s0 = "";
  private String s1 = "";
  private String s2 = "";

  public Calculator() {
    super("Calculator");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create a textfield.
    l = new JTextField(16);
    l.setEditable(false);
    // Create a panel.
    final JPanel panel = new JPanel();
    panel.add(l);
    // Add numbers.
    for (int i = 0; i < 10; ++i) {
      final JButton button = new JButton(Integer.toString(i));
      button.addActionListener(this);
      panel.add(button);
    }
    // Add commands.
    for (final String name : new String[] {"+", "-", "/", "*", "C", ".", "="}) {
      final JButton button = new JButton(name);
      button.addActionListener(this);
      panel.add(button);
    }
    this.add(panel);
    this.setSize(250, 300);
    this.setLocationRelativeTo(null);
  }

  public void actionPerformed(final ActionEvent e) {
    final String s = e.getActionCommand();

    // if the value is a number
    if ((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.charAt(0) == '.') {
      // if operand is present then add to second no
      if (!s1.equals(""))
        s2 = s2 + s;
      else
        s0 = s0 + s;

      // set the value of text
      l.setText(s0 + s1 + s2);
    }
    else if (s.charAt(0) == 'C') {
      // clear the one letter
      s0 = s1 = s2 = "";

      // set the value of text
      l.setText(s0 + s1 + s2);
    }
    else if (s.charAt(0) == '=') {

      double te;

      // store the value in 1st
      if (s1.equals("+"))
        te = (Double.parseDouble(s0) + Double.parseDouble(s2));
      else if (s1.equals("-"))
        te = (Double.parseDouble(s0) - Double.parseDouble(s2));
      else if (s1.equals("/"))
        te = (Double.parseDouble(s0) / Double.parseDouble(s2));
      else
        te = (Double.parseDouble(s0) * Double.parseDouble(s2));

      // set the value of text
      l.setText(s0 + s1 + s2 + "=" + te);

      // convert it to string
      s0 = Double.toString(te);

      s1 = s2 = "";
    }
    else {
      // if there was no operand
      if (s1.equals("") || s2.equals(""))
        s1 = s;
        // else evaluate
      else {
        double te;

        // store the value in 1st
        if (s1.equals("+"))
          te = (Double.parseDouble(s0) + Double.parseDouble(s2));
        else if (s1.equals("-"))
          te = (Double.parseDouble(s0) - Double.parseDouble(s2));
        else if (s1.equals("/"))
          te = (Double.parseDouble(s0) / Double.parseDouble(s2));
        else
          te = (Double.parseDouble(s0) * Double.parseDouble(s2));

        // convert it to string
        s0 = Double.toString(te);

        // place the operator
        s1 = s;

        // make the operand blank
        s2 = "";
      }

      // set the value of text
      l.setText(s0 + s1 + s2);
    }
  }

  public static void main(String[] args) throws InvocationTargetException, InterruptedException {
    SwingUtilities.invokeAndWait(() -> {
      final JFrame frame = new Calculator();
      frame.setVisible(true);
    });
  }

}
