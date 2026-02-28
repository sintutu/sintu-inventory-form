package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PracticePage extends BasePage {

  public PracticePage(WebDriver driver) {
    super(driver);
  }

  @SuppressWarnings("unused")
  @FindBy(id = "login-email")
  private WebElement loginEmail;

  @SuppressWarnings("unused")
  @FindBy(id = "login-password")
  private WebElement loginPassword;

  @SuppressWarnings("unused")
  @FindBy(id = "login-submit")
  private WebElement loginButton;

  public void login(String username, String password) {
    wait.until(ExpectedConditions.visibilityOf(loginEmail)).sendKeys(username);
    loginPassword.sendKeys(password);
    loginButton.click();
  }
}
