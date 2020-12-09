package com.company;

import java.nio.file.Paths;

public class Main {
  public static void main(final String[] args) {
    final String pathToGeckoDriver = Paths.get("geckodriver").toAbsolutePath().toString();
    System.out.printf(pathToGeckoDriver);
    System.setProperty("webdriver.gecko.driver", pathToGeckoDriver);
  }
}
