import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {

    // Enum for supported currencies
    enum Currency {
        EUR, GBP, INR
    }

    // Map to hold exchange rates from USD to other currencies
    private static final Map<Currency, Double> exchangeRates = new HashMap<>();

    static {
        // Static exchange rates (USD -> Other currencies)
        exchangeRates.put(Currency.EUR, 0.91);
        exchangeRates.put(Currency.GBP, 0.77);
        exchangeRates.put(Currency.INR, 83.14);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double amountUSD = getAmountFromUser(scanner);
        Currency targetCurrency = getCurrencyFromUser(scanner);

        double convertedAmount = convertFromUSD(amountUSD, targetCurrency);
        System.out.printf("Converted amount: %.2f %s\n", convertedAmount, targetCurrency);

        scanner.close();
    }

    private static double getAmountFromUser(Scanner scanner) {
        double amount = 0;
        while (true) {
            System.out.print("Enter amount in USD: ");
            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                if (amount >= 0) break;
                else System.out.println("Amount cannot be negative.");
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // clear invalid input
            }
        }
        return amount;
    }

    private static Currency getCurrencyFromUser(Scanner scanner) {
        System.out.println("Choose target currency:");
        for (int i = 0; i < Currency.values().length; i++) {
            System.out.printf("%d. %s\n", i + 1, Currency.values()[i]);
        }

        int choice = 0;
        while (true) {
            System.out.print("Enter choice (1-" + Currency.values().length + "): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= Currency.values().length) break;
                else System.out.println("Invalid choice.");
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // clear invalid input
            }
        }

        return Currency.values()[choice - 1];
    }

    private static double convertFromUSD(double amount, Currency currency) {
        return amount * exchangeRates.get(currency);
    }
}
