package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

  public HomePage(WebDriver driver) {
    super(driver);
  }

  @SuppressWarnings("unused")
  @FindBy(xpath = "//button//span[text()='Login']")
  private WebElement loginButton;

  public void clickLogin() {
    wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    loginButton.click();
  }
}
