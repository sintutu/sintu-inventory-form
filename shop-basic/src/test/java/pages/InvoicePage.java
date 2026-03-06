package pages;

import static utilities.Formatters.formatCurrency;

import models.Invoice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InvoicePage extends BasePage {
  private final WebDriver driver;

  public InvoicePage(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  @FindBy(className = "thank-you")
  private WebElement thankYouOnInvoice;

  public Invoice readInvoice() {
    wait.until(ExpectedConditions.visibilityOf(thankYouOnInvoice));

    WebElement row = driver.findElement(By.xpath("//table//tr[contains(.,'Apple')]"));
    String fullDescription = row.findElement(By.xpath("./td[1]")).getText();
    String deviceName = fullDescription.substring(0, "Apple".length());
    String description = deviceName;
    int quantity = Integer.parseInt(row.findElement(By.xpath("./td[2]")).getText());
    String unitPrice = row.findElement(By.xpath("./td[3]")).getText();
    String totalText =
        driver
            .findElement(By.xpath("//div[@class='total-line final']"))
            .getText()
            .replaceAll("[^0-9.]", "");
    String total = formatCurrency(Double.parseDouble(totalText));

    return new Invoice(description, quantity, unitPrice, total);
  }
}
