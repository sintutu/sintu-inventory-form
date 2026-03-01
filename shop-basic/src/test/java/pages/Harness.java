package pages;

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

  public boolean goToInventoryForm() {
    return new DashboardPage(driver).inventoryFormDisplays();
  }

  public boolean selectDevice(String device) {
    return new DashboardPage(driver).selectDeviceTypeToEnableBrandDropdown(device);
  }

  public boolean selectBrand(String brand) {
    return new DashboardPage(driver).selectBrandToShowDevicePreview(brand);
  }
}
