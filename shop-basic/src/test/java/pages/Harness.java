package pages;

import models.Invoice;
import models.PreviewDetails;
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

  public String selectStorage(String storage) {
    // storage is unused since it's not clear where the price comes from.
    return new DashboardPage(driver).selectStorageReturnsUnitPrice();
  }

  public String getUnitPrice(String storage) {
    // storage is unused since it's not clear where the price comes from.
    return new DashboardPage(driver).getUnitPrice();
  }

  public String selectColour(String colour) {
    return new DashboardPage(driver).selectColourToShowColourLabel(colour);
  }

  public String updateDeviceQuantity(int quantity) {
    return new DashboardPage(driver).subtotalUpdatesWhenEnteringQuantity(quantity);
  }

  public String getSubtotal() {
    return new DashboardPage(driver).getSubtotal();
  }

  public String enterAddress(String address) {
    return new DashboardPage(driver).setAddress(address);
  }

  public PreviewDetails clickNextAndGetPreview(String brand, String colour, String storage) {
    return new DashboardPage(driver).clickNextShowsOrderReview(brand, colour, storage);
  }

  public String addExpressShipping() {
    return new DashboardPage(driver).addExpressShippingIncreasesTotalCost();
  }

  public String addOneYearWarranty() {
    return new DashboardPage(driver).addOneYearWarrantyIncreasesTotalCost();
  }

  public String useSAVE10discountCode() {
    return new DashboardPage(driver).applyDiscount();
  }

  public String confirmPurchase() {
    return new DashboardPage(driver).confirmPurchaseShowsSuccessToast();
  }

  public String viewInvoiceHistory() {
    return new DashboardPage(driver).clickInvoiceButtonShowsInvoiceHistory();
  }

  public Invoice viewLatestInvoice() {
    return new DashboardPage(driver).openLatestInvoice();
  }
}
