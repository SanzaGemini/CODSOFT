package codsoft.org;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.IllegalFormatConversionException;
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

    private int amount;

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
        //https://openexchangerates.org/api/currencies.json?app_id=79b376529ee94cbea8fb9308fa9e6cfc
        //"https://openexchangerates.org/api/latest.json?app_id=79b376529ee94cbea8fb9308fa9e6cfc&base="
        URL url = new URL("https://openexchangerates.org/api/latest.json?app_id=79b376529ee94cbea8fb9308fa9e6cfc");
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
    public int getAmount() {
        System.out.println("Please Enter The Amount: ");
        String stringAmount = this.scanner.nextLine();
        return validateAmount(stringAmount) ? Integer.parseInt(stringAmount): getAmount();
    }

    private boolean validateAmount(String stringAmount){
        try{
            int intAmount = Integer.parseInt(stringAmount);
            return intAmount >= 0;
        } catch (IllegalFormatConversionException | NumberFormatException e){
            return false;
        }
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }



    private JsonObject exchangeAmount() throws IOException {
        String exchangeRate = getExchangeRate();
        Gson gson = new Gson();
        return gson.fromJson(exchangeRate, JsonObject.class).get("rates").getAsJsonObject();
    }

    private float getCurrencyValue(JsonObject exchangeRate,String code){
        return exchangeRate.get(code).getAsFloat();
    }
    private float calcBaseToUSD(float baseValue){
        if (baseValue <1) {
            return amount / baseValue;
        } return amount * baseValue;
    }

    public float calcAmount(float baseValue,float targetValue){
        float exchangeRate = calcBaseToUSD(baseValue);
        return exchangeRate * targetValue;
    }
    public void calcExchangeAmount() throws IOException {
        JsonObject exchangeRates = exchangeAmount();
        float baseCurrencyVsUsd = getCurrencyValue(exchangeRates,baseCurrency);
        float targetCurrencyVsUsd = getCurrencyValue(exchangeRates,targetCurrency);
        System.out.println(baseCurrency+": " + amount);
        System.out.println(targetCurrency+": "+ calcAmount(baseCurrencyVsUsd,targetCurrencyVsUsd));
    }
    public static void main(String[] args) throws IOException {
        CurrencyConverter converter = new CurrencyConverter();
        do{
            converter.setBaseCurrency(converter.getCurrency("Base"));
            converter.setTargetCurrency(converter.getCurrency("Target"));
            converter.setAmount(converter.getAmount());
            converter.calcExchangeAmount();
        } while (converter.convertAgain());
    }
}
