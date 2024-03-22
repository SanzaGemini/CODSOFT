package codsoft.org;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
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
    private String targetCurrency;

    public CurrencyConverter(){
        this.scanner = new Scanner(System.in);
    }
    public CurrencyConverter(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public String getCurrency(String type) {
        int option = 1;
        System.out.println("Select "+type+" Currency From the List Below: ");
        for (String currency:currencyCodes
             ) {
            System.out.println(option +": "+ currency);
            option++;
        }
        String currency = scanner.nextLine();

        return validateCurrency(currency) ? currency : getCurrency(type) ;
    }

    private boolean validateCurrency(String currency){
        return currencyCodes.contains(currency);
    }

    public void setBaseCurrency(String currency){
        this.baseCurrency = currency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public boolean convertAgain(){
        System.out.println("Would You Like To Convert Again? (Yes/Y or No/N)");
        String answer = scanner.nextLine();
        return answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("y");
    }

    private String getExchangeRate() throws IOException {

        URL url = new URL("https://openexchangerates.org/api/latest.json?app_id=9aa1826c22-d7ede20f04-sar57z");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            return getResponse(conn);
        } else {
            System.out.println("Failed to fetch exchange rates. Response code: " + responseCode);
            return null;
        }
    }

    private String getResponse(HttpURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        do{
            converter.setBaseCurrency(converter.getCurrency("Base"));
            converter.setTargetCurrency(converter.getCurrency("Target"));
        } while (converter.convertAgain());
    }



}
