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
/**
 * A simple currency converter that converts between different currencies using the latest exchange rates.
 */
public class CurrencyConverter {

    private final Scanner scanner;
    // List of supported currency codes
    private final List<String> currencyCodes =List.of(
            "USD", "EUR", "JPY", "GBP", "AUD", "CAD", "CHF"
            // Add more currency codes as needed
    );

    private String baseCurrency;
    private String targetCurrency;

    private int amount;
    /**
     * Constructs a new CurrencyConverter with the default input source (System.in).
     */
    public CurrencyConverter(){
        this.scanner = new Scanner(System.in);
    }
    /**
     * Constructs a new CurrencyConverter with the specified input stream.
     * @param inputStream The input stream to use for user input.
     */
    public CurrencyConverter(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }
    /**
     * Prompts the user to select a currency from the list of supported currencies.
     * @param type The type of currency being selected (e.g., "Base" or "Target").
     * @return The selected currency code.
     */
    public String getCurrency(String type) {

        // Display the list of supported currencies
        int option = 1;
        System.out.println("Select "+type+" Currency From the List Below: ");
        for (String currency:currencyCodes
             ) {
            System.out.println(option +": "+ currency);
            option++;
        }
        // Prompt the user to select a currency
        String currency = scanner.nextLine();
        // Validate the user input
        // Return the selected currency
        return validateCurrency(currency) ? currency : getCurrency(type) ;
    }
    /**
     * Validates whether the given currency code is supported.
     * @param currency The currency code to validate.
     * @return True if the currency code is supported, false otherwise.
     */
    private boolean validateCurrency(String currency){
        return currencyCodes.contains(currency);
    }
    /**
     * Sets the base currency for the conversion.
     * @param currency The base currency code.
     */
    public void setBaseCurrency(String currency){
        this.baseCurrency = currency;
    }
    /**
     * Sets the target currency for the conversion.
     * @param targetCurrency The target currency code.
     */
    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }
    /**
     * Prompts the user to decide whether to perform another currency conversion.
     * The method displays a message asking the user if they would like to convert again,
     * and then reads the user's input from the console.
     *
     * @return True if the user chooses to convert again by entering "Yes" or "Y" (case-insensitive),
     *         false otherwise.
     */
    public boolean convertAgain(){
        // Prompt the user to decide whether to convert again
        System.out.println("Would You Like To Convert Again? (Yes/Y or No/N)");

        // Read the user's input from the console
        String answer = scanner.nextLine();

        // Return true if the user enters "Yes" or "Y" (case-insensitive), false otherwise
        return answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("y");
    }
    /**
     * Fetches the latest exchange rates from an external API.
     * @return The JSON string containing exchange rates.
     * @throws IOException If an I/O error occurs while fetching exchange rates.
     */
    private String getExchangeRate() throws IOException {
        //https://openexchangerates.org/api/currencies.json?app_id=79b376529ee94cbea8fb9308fa9e6cfc
        //"https://openexchangerates.org/api/latest.json?app_id=79b376529ee94cbea8fb9308fa9e6cfc&base="

        // Construct URL for fetching exchange rates
        URL url = new URL("https://openexchangerates.org/api/latest.json?app_id=79b376529ee94cbea8fb9308fa9e6cfc");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // Send HTTP request to the API
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read response from the API
            // Return the JSON string containing exchange rates
            return getResponse(conn);
        } else {
            System.out.println("Failed to fetch exchange rates. Response code: " + responseCode);
            return null;
        }
    }
    /**
     * Reads the response from the HttpURLConnection and constructs a String representing the response.
     * This method reads the input stream from the HttpURLConnection, line by line, and appends each line
     * to a StringBuilder to construct the complete response.
     *
     * @param connection The HttpURLConnection from which to read the response.
     * @return A String representing the response received from the HttpURLConnection.
     * @throws IOException If an I/O error occurs while reading the response.
     */
    private String getResponse(HttpURLConnection connection) throws IOException {
        // Create a BufferedReader to read the input stream from the HttpURLConnection
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        // Initialize a StringBuilder to construct the complete response
        String inputLine;
        StringBuilder response = new StringBuilder();

        // Read the input stream line by line and append each line to the StringBuilder
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        // Close the BufferedReader
        in.close();

        // Return the complete response as a String
        return response.toString();
    }
    /**
     * Prompts the user to enter an amount to convert.
     * @return The amount entered by the user.
     */
    public int getAmount() {
        // Prompt the user to enter an amount
        System.out.println("Please Enter The Amount: ");
        // Validate the user input
        String stringAmount = this.scanner.nextLine();
        // Return the entered amount
        return validateAmount(stringAmount) ? Integer.parseInt(stringAmount): getAmount();
    }
    /**
     * Validates whether the given currency code is supported.
     * @param stringAmount The currency code to validate.
     * @return True if the currency code is supported, false otherwise.
     */
    private boolean validateAmount(String stringAmount){
        try{
            int intAmount = Integer.parseInt(stringAmount);
            return intAmount >= 0;
        } catch (IllegalFormatConversionException | NumberFormatException e){
            return false;
        }
    }
    /**
     * Sets the amount to be converted.
     * @param amount The amount to convert.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
    /**
     * Parses the JSON string to extract exchange rates for different currencies.
     * @return A JsonObject containing exchange rates.
     */
    private JsonObject exchangeAmount() throws IOException {
        String exchangeRate = getExchangeRate();
        // Parse the JSON string
        Gson gson = new Gson();
        // Extract exchange rates for different currencies
        // Return a JsonObject containing exchange rates
        return gson.fromJson(exchangeRate, JsonObject.class).get("rates").getAsJsonObject();
    }
    /**
     * Retrieves the exchange rate for a specific currency from the JsonObject.
     * @param exchangeRate The JsonObject containing exchange rates.
     * @param code The currency code to retrieve the exchange rate for.
     * @return The exchange rate for the specified currency.
     */
    private float getCurrencyValue(JsonObject exchangeRate,String code){
        // Retrieve the exchange rate for the specified currency from the JsonObject
        // Return the exchange rate as a float
        return exchangeRate.get(code).getAsFloat();
    }
    private float calcBaseToUSD(float baseValue){
        return amount / baseValue;
    }

    /**
     * Calculates the amount in the target currency based on the provided exchange rates.
     * @param baseValue The exchange rate from the base currency to USD.
     * @param targetValue The exchange rate from USD to the target currency.
     * @return The converted amount in the target currency.
     */
    public float calcAmount(float baseValue,float targetValue){
        // Calculate the amount in the USD currency
        float exchangeRate = calcBaseToUSD(baseValue);
        // Round the result to two decimal places
        // Return the rounded amount
        return (float) ((float) Math.round(exchangeRate * targetValue*100.0)/100.0);
    }
    /**
     * Performs the currency conversion and prints the result.
     * @throws IOException If an I/O error occurs while fetching exchange rates.
     */
    public void calcExchangeAmount() throws IOException {
        // Fetch the latest exchange rates
        // Parse the exchange rates
        JsonObject exchangeRates = exchangeAmount();
        // Get the exchange rates for base and target currencies
        float baseCurrencyVsUsd = getCurrencyValue(exchangeRates,baseCurrency);
        float targetCurrencyVsUsd = getCurrencyValue(exchangeRates,targetCurrency);
        // Calculate the amount in the target currency
        // Print the conversion result
        System.out.println(baseCurrency+": " + amount);
        System.out.println(targetCurrency+": "+ calcAmount(baseCurrencyVsUsd,targetCurrencyVsUsd));
    }
    /**
     * Main method to execute the currency conversion process.
     * @param args Command-line arguments (not used).
     * @throws IOException If an I/O error occurs while fetching exchange rates.
     */
    public static void main(String[] args) throws IOException {
        // Create a new instance of CurrencyConverter
        CurrencyConverter converter = new CurrencyConverter();
        do{
            // Prompt the user to select base and target currencies
            converter.setBaseCurrency(converter.getCurrency("Base"));
            converter.setTargetCurrency(converter.getCurrency("Target"));
            // Prompt the user to enter the amount to convert
            converter.setAmount(converter.getAmount());
            // Perform the currency conversion and print the result
            converter.calcExchangeAmount();
            // Repeat the conversion process if the user wants to convert again
        } while (converter.convertAgain());
    }
}
