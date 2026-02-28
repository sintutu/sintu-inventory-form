package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {

  public DashboardPage(WebDriver driver) {
    super(driver);
  }

  @SuppressWarnings("unused")
  @FindBy(xpath = "//p[normalize-space()=\"Here's an overview of your learning journey\"]")
  private WebElement dashboardSubtext;

  public boolean dashboardWelcomeMessageDisplays() {
    return wait.until(ExpectedConditions.visibilityOf(dashboardSubtext)).isDisplayed();
  }
}
