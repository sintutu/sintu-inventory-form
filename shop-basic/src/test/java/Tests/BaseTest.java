package Tests;

import Pages.Harness;
import Utilities.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

  protected WebDriver driver;

  protected Harness app;

  @BeforeMethod
  public void setUp() {
    app =
        new Harness(
            new BrowserFactory().startBrowser("https://ndosisimplifiedautomation.vercel.app/"));
  }

  @AfterMethod
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
