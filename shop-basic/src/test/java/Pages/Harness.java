package Pages;

import org.openqa.selenium.WebDriver;

public class Harness {
  private final WebDriver driver;

  public Harness(WebDriver driver) {
    this.driver = driver;
  }

  public boolean login(String username, String password) {
    new HomePage(driver).clickLogin();
    new PracticePage(driver).login(username, password);
    DashboardPage dashboardPage = new DashboardPage(driver);

    return dashboardPage.dashboardWelcomeMessageDisplays();
  }
}
