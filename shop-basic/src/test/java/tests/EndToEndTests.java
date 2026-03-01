package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndTests extends BaseTest {
  @Test
  public void loginPurchaseInvoice() {
    Assert.assertTrue(app.login("sintutu@dev.com", "@987654321"), "User failed to log in");
    Assert.assertTrue(app.goToInventoryForm(), "Inventory Form should be visible");
    Assert.assertTrue(
        app.selectDevice(), "Selecting Device Type phone should enable Brand dropdown.");
  }
}
