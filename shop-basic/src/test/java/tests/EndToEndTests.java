package tests;

import io.qameta.allure.Feature;
import models.Invoice;
import models.PreviewDetails;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndTests extends BaseTest {
  @Feature("happy-path")
  @Test(groups = {"happy-path"})
  public void loginPurchaseInvoice() {
    Assert.assertTrue(app.login("sintutu@dev.com", "@987654321"), "User failed to log in.");
    Assert.assertTrue(app.goToInventoryForm(), "Inventory Form should be visible.");
    Assert.assertTrue(
        app.selectDevice("Phone"), "Selecting Device Type phone should enable Brand dropdown.");
    Assert.assertTrue(app.selectBrand("Apple"), "Selecting Brand should show device preview.");
    Assert.assertEquals(
        app.selectStorage("128GB"), app.getUnitPrice("128GB"), "Unit price is incorrect.");
    Assert.assertEquals(
        app.selectColour("Blue"), "Blue".toLowerCase(), "Colour displays incorrectly.");
    Assert.assertEquals(app.updateDeviceQuantity(2), app.getSubtotal(), "Subtotal is incorrect.");
    Assert.assertEquals(
        app.enterAddress("123 Test Street"), "123 Test Street", "Address is incorrect.");
    Assert.assertEquals(
        app.clickNextAndGetPreview("Apple", "Blue", "128GB"),
        new PreviewDetails("Apple", "Blue".toLowerCase(), "128GB"),
        "Preview does not show what was selected.");
    Assert.assertEquals(
        app.addExpressShipping(), "R985.00", "Express shipping cost miscalculated.");
    Assert.assertEquals(app.addOneYearWarranty(), "R1034.00", "Warranty cost miscalculated.");
    Assert.assertEquals(app.useSAVE10discountCode(), "R930.60", "Discount miscalculated.");
    Assert.assertEquals(app.confirmPurchase(), "Order Details:", "Toast message incorrect.");
    Assert.assertEquals(
        app.viewInvoiceHistory(), "\uD83D\uDCC4 Invoice History", "Invoice history not displayed.");
    Assert.assertEquals(
        app.viewLatestInvoice(),
        new Invoice("Apple", 2, "R480.00", "R930.60"),
        "Invoice is incorrect.");
  }
}
