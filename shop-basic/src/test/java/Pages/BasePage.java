package Pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

  protected final WebDriverWait wait;

  public BasePage(WebDriver driver) {
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    PageFactory.initElements(driver, this);
  }
}
