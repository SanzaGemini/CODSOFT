package codsoft.org;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.net.HttpURLConnection;

public class CurrencyConverter {

    private final Scanner scanner;
    private final List<String> currencyCodes =List.of(
            "USD", "EUR", "JPY", "GBP", "AUD", "CAD", "CHF"
            // Add more currency codes as needed
    );

    private String baseCurrency;

    public CurrencyConverter(){
        this.scanner = new Scanner(System.in);
    }
    public CurrencyConverter(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public String getBaseCurrency() {
        int option = 1;
        System.out.println("Select Base Currency From the List Below: ");
        for (String currency:currencyCodes
             ) {
            System.out.println(option +": "+ currency);
            option++;
        }
        baseCurrency = scanner.nextLine();

        return validateCurrency(baseCurrency) ? baseCurrency : getBaseCurrency() ;
    }

    private boolean validateCurrency(String currency){
        return currencyCodes.contains(currency);
    }

}
