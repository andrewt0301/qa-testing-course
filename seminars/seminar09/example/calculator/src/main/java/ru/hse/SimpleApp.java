package ru.hse;

import java.lang.reflect.InvocationTargetException;

import javax.swing.*;

public class SimpleApp extends JFrame {
  private static final long serialVersionUID = 1L;

  public SimpleApp() {
    super("SimpleApp");
    setName("SimpleApp");

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(260, 200);
    setLocationRelativeTo(null);
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

    button.addActionListener(e -> label.setText(textField.getText()));
  }

  public static void main(final String[] args) throws InvocationTargetException, InterruptedException {
    SwingUtilities.invokeAndWait(() -> {
      final JFrame frame = new SimpleApp();
      frame.setVisible(true);

      frame.setExtendedState(JFrame.ICONIFIED);
      frame.setExtendedState(JFrame.NORMAL);
      frame.toFront();
      frame.requestFocus();
    });
  }

}
