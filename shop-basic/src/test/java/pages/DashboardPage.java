package pages;

import models.PreviewDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

  @SuppressWarnings("unused")
  @FindBy(id = "storage-128GB")
  private WebElement storage128GBRadioButton;

  @SuppressWarnings("unused")
  @FindBy(css = "[data-testid='unit-price-value']")
  private WebElement unitPrice;

  public String selectStorageReturnsUnitPrice() {
    wait.until(ExpectedConditions.elementToBeClickable(storage128GBRadioButton)).click();
    return formatCurrency(480);
  }

  public String getUnitPrice() {
    return unitPrice.getText();
  }

  private String selectFromDropdown(WebElement webElement, String option) {
    Select select = new Select(webElement);
    select.selectByValue(option);
    return select.getFirstSelectedOption().getText();
  }

  @SuppressWarnings("unused")
  @FindBy(id = "color")
  private WebElement deviceColourDropdown;

  public String selectColourToShowColourLabel(String deviceColour) {
    String colour = deviceColour.toLowerCase();
    wait.until(ExpectedConditions.elementToBeClickable(deviceColourDropdown));

    String selectedColor = selectFromDropdown(deviceColourDropdown, colour);

    By previewColorLocator =
        By.xpath(
            String.format(
                "//div[@id='device-preview']//strong[normalize-space()='%s']",
                selectedColor.toLowerCase()));

    return wait.until(ExpectedConditions.visibilityOfElementLocated(previewColorLocator)).getText();
  }

  private WebElement getPreviewBrand(String brand) {
    String previewXPath =
        String.format("//div[@id='device-preview']//img[contains(@alt,'%s')]", brand);
    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(previewXPath)));
  }

  private String formatCurrency(double amount) {
    return String.format("R%.2f", amount);
  }

  @SuppressWarnings("unused")
  @FindBy(css = "[data-testid='quantity-input']")
  private WebElement quantityInput;

  @SuppressWarnings("unused")
  @FindBy(css = "[data-testid='subtotal-value']")
  private WebElement subtotalValue;

  public String subtotalUpdatesWhenEnteringQuantity(int quantity) {
    wait.until(ExpectedConditions.elementToBeClickable(quantityInput));
    quantityInput.clear();
    quantityInput.sendKeys(String.valueOf(quantity));
    quantityInput.sendKeys(Keys.TAB);
    return formatCurrency(quantity * 480);
  }

  public String getSubtotal() {
    return subtotalValue.getText();
  }

  @SuppressWarnings("unused")
  @FindBy(css = "[data-testid='address-input']")
  private WebElement addressInput;

  public String setAddress(String address) {
    addressInput.clear();
    addressInput.sendKeys(address);
    return addressInput.getAttribute("value");
  }

  @FindBy(css = "[data-testid='inventory-next-btn']")
  private WebElement inventoryNextButton;

  @FindBy(css = "[data-testid='device-preview-wrapper']")
  private WebElement devicePreviewWrapper;

  private By getPreviewBrandLocator(String brand) {
    String previewXPath =
        String.format(
            "//div[@data-testid='device-preview-wrapper']//div[normalize-space()='%s']", brand);
    return By.xpath(previewXPath);
  }

  private By getPreviewColourLocator(String colour) {
    String previewXPath =
        String.format(
            "//div[@data-testid='device-preview-wrapper']//div[contains(., 'Color:')]//strong[normalize-space()='%s']",
            colour.toLowerCase());
    return By.xpath(previewXPath);
  }

  private By getPreviewStorageLocator(String storage) {
    String previewXPath =
        String.format(
            "//div[@data-testid='device-preview-wrapper']//div[contains(., 'Storage:')]//strong[normalize-space()='%s']",
            storage);
    return By.xpath(previewXPath);
  }

  public PreviewDetails clickNextShowsOrderReview(
      String selectedBrand, String selectedColour, String selectedStorage) {
    inventoryNextButton.click();
    wait.until(ExpectedConditions.visibilityOf(devicePreviewWrapper));

    String brand =
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    getPreviewBrandLocator(selectedBrand)))
            .getText()
            .trim();
    String colour =
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    getPreviewColourLocator(selectedColour)))
            .getText()
            .trim();
    String storage =
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    getPreviewStorageLocator(selectedStorage)))
            .getText()
            .trim();

    return new PreviewDetails(brand, colour, storage);
  }
}
