package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

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

  @SuppressWarnings("unused")
  @FindBy(
      xpath =
          "//button[contains(@class,'nav-dropdown-trigger') and .//span[normalize-space(text())='Learn']]")
  private WebElement learnNavigationButton;

  @SuppressWarnings("unused")
  @FindBy(
      xpath =
          "//button[contains(@class,'nav-dropdown-item') and .//span[normalize-space(text())='Learning Materials']]")
  private WebElement learningMaterialsButton;

  @SuppressWarnings("unused")
  @FindBy(id = "tab-btn-web")
  private WebElement webAutomationAdvanceButton;

  @SuppressWarnings("unused")
  @FindBy(id = "inventory-title")
  private WebElement inventoryFormHeading;

  @SuppressWarnings("unused")
  @FindBy(id = "deviceType")
  private WebElement deviceTypeDropdown;

  @SuppressWarnings("unused")
  @FindBy(id = "brand")
  private WebElement brandDropdown;

  public boolean inventoryFormDisplays() {
    wait.until(ExpectedConditions.elementToBeClickable(learnNavigationButton)).click();
    wait.until(ExpectedConditions.elementToBeClickable(learningMaterialsButton)).click();
    wait.until(ExpectedConditions.elementToBeClickable(webAutomationAdvanceButton)).click();
    return wait.until(ExpectedConditions.visibilityOf(inventoryFormHeading)).isDisplayed();
  }

  public boolean selectDeviceTypeToEnableBrandDropdown(String deviceType) {
    wait.until(ExpectedConditions.elementToBeClickable(deviceTypeDropdown));
    selectFromDropdown(deviceTypeDropdown, deviceType.toLowerCase());
    return brandDropdown.isDisplayed();
  }

  public boolean selectBrandToShowDevicePreview(String brand) {
    wait.until(ExpectedConditions.elementToBeClickable(brandDropdown));
    selectFromDropdown(brandDropdown, brand.toLowerCase());
    return getPreviewBrand(brand).isDisplayed();
  }

  private void selectFromDropdown(WebElement webElement, String deviceType) {
    Select select = new Select(webElement);
    select.selectByValue(deviceType);
    System.out.println(select.getFirstSelectedOption().getText());
  }

  private WebElement getPreviewBrand(String brand) {
    String previewXPath =
        String.format("//div[@id='device-preview']//img[contains(@alt,'%s')]", brand);
    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(previewXPath)));
  }
}
