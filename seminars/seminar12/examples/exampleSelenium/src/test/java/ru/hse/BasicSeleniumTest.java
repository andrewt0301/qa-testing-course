package ru.hse;

import org.junit.Ignore;
import org.junit.Test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;

public class BasicSeleniumTest {

  /**
   * Runs a simple test in Safari.
   * <p>
   * Marked as {@code @Ignore} because there is no information on the environment.
   * If you are using Safari, just run it manually or remove {@code @Ignore}.
   */
  @Ignore
  @Test
  public void testSafari() {
    // In MacOS, WebDriver for Safari is already installed in this folder: '/usr/bin/safaridriver'.
    // No additional setup is required.
    simpleTest(new SafariDriver());
  }

  /**
   * Runs a simple test in Chrome.
   * <p>
   * Marked as {@code @Ignore} because there is no information on the environment.
   * If you are using Chrome, just run it manually or remove {@code @Ignore}.
   */
  @Ignore
  @Test
  public void testChrome() {
    // Proper version of chromedriver can be downloaded from here:
    // https://chromedriver.chromium.org
    final String pathToDriver = Paths.get("chromedriver").toAbsolutePath().toString();
    System.setProperty("webdriver.chrome.driver", pathToDriver);
    // Now we run the test.
    simpleTest(new ChromeDriver());
  }

  /**
   * Runs a simple test in Firefox.
   * <p>
   * Marked as {@code @Ignore} because there is no information on the environment.
   * If you are using Firefox, just run it manually or remove {@code @Ignore}.
   */
  @Ignore
  @Test
  public void testFirefox() {
    // Proper version of geckodriver can be downloaded from here:
    // https://github.com/mozilla/geckodriver/releases
    final String pathToGeckoDriver = Paths.get("geckodriver").toAbsolutePath().toString();
    System.setProperty("webdriver.gecko.driver", pathToGeckoDriver);
    // Now we run the test.
    simpleTest(new FirefoxDriver());
  }

  /**
   * Simple web test that can be run in any browser.
   *
   * @param driver Web drive for any browser type.
   */
  private void simpleTest(final WebDriver driver) {
    // And now use this to visit Google
    driver.get("http://www.google.com");
    // Alternatively the same thing can be done like this
    // driver.navigate().to("http://www.google.com");
    // Find the text input element by its name
    final WebElement element = driver.findElement(By.name("q"));
    // Enter something to search for
    element.sendKeys("Cheese!");
    // Now submit the form. WebDriver will find the form for us from the element
    element.submit();
    // Check the title of the page
    System.out.println("Page title is: " + driver.getTitle());
    // Google's search is rendered dynamically with JavaScript.
    // Wait for the page to load, timeout after 10 seconds
    (new WebDriverWait(driver, 10)).until(
        d -> d.getTitle().toLowerCase().startsWith("cheese!")
    );
    // Should see: "cheese! - Google Search"
    System.out.println("Page title is: " + driver.getTitle());
    //Close the browser
    driver.quit();
  }

}
