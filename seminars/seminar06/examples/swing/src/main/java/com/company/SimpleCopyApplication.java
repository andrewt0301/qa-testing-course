package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;

public class SimpleCopyApplication extends JFrame {
  private static final long serialVersionUID = 1L;

  public SimpleCopyApplication() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(260, 200);
    setLayout(null);

    final JTextField textField = new JTextField();
    textField.setName("textToCopy");

    textField.setBounds(20,20,200, 20);
    add(textField);

    final JButton button = new JButton("Copy text to label");
    button.setName("copyButton");

    button.setBounds(40,60,160, 20);
    add(button);

    final JLabel label = new JLabel();
    label.setName("copiedText");

    label.setBounds(20,100,200, 20);
    add(label);

    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        label.setText(textField.getText());
      }
    });
  }

  public static void main(String[] args) throws InvocationTargetException, InterruptedException {
    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        JFrame frame = new SimpleCopyApplication();
        frame.setVisible(true);
      }
    });
  }
}

