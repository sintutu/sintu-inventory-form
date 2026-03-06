package pages;

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

  public boolean login(String username, String password) {
    new HomePage(driver).clickLogin();
    new PracticePage(driver).login(username, password);
    return dashboardPage.dashboardWelcomeMessageDisplays();
  }

  public boolean goToInventoryForm() {
    return dashboardPage.inventoryFormDisplays();
  }

  public boolean selectDevice(String device) {
    return dashboardPage.selectDeviceTypeToEnableBrandDropdown(device);
  }

  public boolean selectBrand(String brand) {
    return dashboardPage.selectBrandToShowDevicePreview(brand);
  }

  public String selectStorage(String storage) {
    // storage is unused since it's not clear where the price comes from.
    return dashboardPage.selectStorageReturnsUnitPrice();
  }

  public String getUnitPrice(String storage) {
    // storage is unused since it's not clear where the price comes from.
    return dashboardPage.getUnitPrice();
  }

  public String selectColour(String colour) {
    return dashboardPage.selectColourToShowColourLabel(colour);
  }

  public String updateDeviceQuantity(int quantity) {
    return dashboardPage.subtotalUpdatesWhenEnteringQuantity(quantity);
  }

  public String getSubtotal() {
    return dashboardPage.getSubtotal();
  }

  public String enterAddress(String address) {
    return dashboardPage.setAddress(address);
  }

  public PreviewDetails clickNextAndGetPreview(String brand, String colour, String storage) {
    return dashboardPage.clickNextShowsOrderReview(brand, colour, storage);
  }

  public String addExpressShipping() {
    return dashboardPage.addExpressShippingIncreasesTotalCost();
  }

  public String addOneYearWarranty() {
    return dashboardPage.addOneYearWarrantyIncreasesTotalCost();
  }

  public String useSAVE10discountCode() {
    return dashboardPage.applyDiscount();
  }

  public String confirmPurchase() {
    return dashboardPage.confirmPurchaseShowsSuccessToast();
  }

  public String viewInvoiceHistory() {
    return dashboardPage.clickInvoiceButtonShowsInvoiceHistory();
  }

  public Invoice viewLatestInvoice() {
    return dashboardPage.openLatestInvoice();
  }
}
