package utilities;

public class Formatters {
  public static String formatCurrency(double amount) {
    return String.format("R%.2f", amount);
  }

  public static double parseCurrency(String text) {
    return Double.parseDouble(text.replace("R", "").trim());
  }
}
