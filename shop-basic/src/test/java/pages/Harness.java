package pages;

import io.qameta.allure.Step;
import models.Invoice;
import models.PreviewDetails;
import org.openqa.selenium.WebDriver;

public class Harness {
  private final WebDriver driver;
  private DashboardPage dashboardPage;

  public Harness(WebDriver driver) {
    this.driver = driver;
    dashboardPage = new DashboardPage(driver);
  }

  @Step("Log in")
  public boolean login(String username, String password) {
    new HomePage(driver).clickLogin();
    new PracticePage(driver).login(username, password);
    return dashboardPage.dashboardWelcomeMessageDisplays();
  }

  @Step("Go to inventory form")
  public boolean goToInventoryForm() {
    return dashboardPage.inventoryFormDisplays();
  }

  @Step("Select device: {device}")
  public boolean selectDevice(String device) {
    return dashboardPage.selectDeviceTypeToEnableBrandDropdown(device);
  }

  @Step("Select brand: {brand}")
  public boolean selectBrand(String brand) {
    return dashboardPage.selectBrandToShowDevicePreview(brand);
  }

  @Step("Select storage: {storage}")
  public String selectStorage(String storage) {
    // storage is unused since it's not clear where the price comes from.
    return dashboardPage.selectStorageReturnsUnitPrice();
  }

  public String getUnitPrice(String storage) {
    // storage is unused since it's not clear where the price comes from.
    return dashboardPage.getUnitPrice();
  }

  @Step("Select colour: {colour}")
  public String selectColour(String colour) {
    return dashboardPage.selectColourToShowColourLabel(colour);
  }

  @Step("Update device quantity to {quantity}")
  public String updateDeviceQuantity(int quantity) {
    return dashboardPage.subtotalUpdatesWhenEnteringQuantity(quantity);
  }

  public String getSubtotal() {
    return dashboardPage.getSubtotal();
  }

  @Step("Enter address {address}")
  public String enterAddress(String address) {
    return dashboardPage.setAddress(address);
  }

  @Step("Go to order review")
  public PreviewDetails clickNextAndGetPreview(String brand, String colour, String storage) {
    return dashboardPage.clickNextShowsOrderReview(brand, colour, storage);
  }

  @Step("Add express shipping")
  public String addExpressShipping() {
    return dashboardPage.addExpressShippingIncreasesTotalCost();
  }

  @Step("Add 1 year warranty")
  public String addOneYearWarranty() {
    return dashboardPage.addOneYearWarrantyIncreasesTotalCost();
  }

  @Step("Apply SAVE10 discount code")
  public String useSAVE10discountCode() {
    return dashboardPage.applyDiscount();
  }

  @Step("Confirm purchase")
  public String confirmPurchase() {
    return dashboardPage.confirmPurchaseShowsSuccessToast();
  }

  @Step("Open invoice history")
  public String viewInvoiceHistory() {
    return dashboardPage.clickInvoiceButtonShowsInvoiceHistory();
  }

  @Step("Open latest invoice")
  public Invoice viewLatestInvoice() {
    return dashboardPage.openLatestInvoice();
  }
}
