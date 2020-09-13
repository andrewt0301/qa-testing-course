package ru.hse;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.*;

public class RobotExample {
  public static int KEY_DELAY = 300;

  public static void main(String[] args) throws IOException, AWTException {
    final String command = "notepad.exe";

    Runtime run = Runtime.getRuntime();
    run.exec(command);

    Robot robot = new Robot();
    robot.delay(1000);

    sendKeys(robot, KeyEvent.VK_H,
                    KeyEvent.VK_E,
                    KeyEvent.VK_L,
                    KeyEvent.VK_L,
                    KeyEvent.VK_O,
                    KeyEvent.VK_SPACE);

    sendKeys(robot, KeyEvent.VK_W,
                    KeyEvent.VK_O,
                    KeyEvent.VK_R,
                    KeyEvent.VK_L,
                    KeyEvent.VK_D,
                    KeyEvent.VK_SPACE);
  }

  private static void sendKeys(Robot robot, int ... keys) {
     for (int key : keys) {
         sendKey(robot, key);
     }
  }

  private static void sendKey(Robot robot, int key) {
    robot.delay(KEY_DELAY);
    robot.keyPress(key);
  }
}
