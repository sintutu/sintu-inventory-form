package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFactory {
  public WebDriver driver;

  public WebDriver startBrowser(String url) {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get(url);
    return driver;
  }
}
