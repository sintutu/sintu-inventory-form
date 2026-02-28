package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndTests extends BaseTest {
  @Test
  public void loginPurchaseInvoice() {
    Assert.assertTrue(app.login("sintutu@dev.com", "@987654321"), "User failed to log in");
  }
}
