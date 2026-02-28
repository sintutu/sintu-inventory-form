package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.Harness;
import utilities.BrowserFactory;

public abstract class BaseTest {

  protected WebDriver driver;

  protected Harness app;

  @BeforeMethod
  public void setUp() {
    driver = new BrowserFactory().startBrowser("https://ndosisimplifiedautomation.vercel.app/");
    app = new Harness(driver);
  }

  @AfterMethod
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
