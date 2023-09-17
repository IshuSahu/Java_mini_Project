package Level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyConverter {
    public static void main(String[] args) {
        try {
            String apiKey = "e759f92560e41c99ee6213a2"; // Replace with your API key
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter the base currency (e.g., USD): ");
            String fromCurrency = reader.readLine().toUpperCase();

            System.out.print("Enter the target currency (e.g., EUR): ");
            String toCurrency = reader.readLine().toUpperCase();

            System.out.print("Enter the amount: ");
            double amount = Double.parseDouble(reader.readLine());

            double exchangeRate = getExchangeRate(apiKey, fromCurrency, toCurrency);
            if (exchangeRate == -1) {
                System.out.println("Failed to retrieve exchange rate. Please check your inputs.");
            } else {
                double convertedAmount = amount * exchangeRate;
                System.out.printf("%.2f %s = %.2f %s%n", amount, fromCurrency, convertedAmount, toCurrency);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double getExchangeRate(String apiKey, String fromCurrency, String toCurrency) {
        try {
            String urlStr = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + fromCurrency + "/" + toCurrency;
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                return jsonResponse.getDouble("conversion_rate");
            } else {
                System.err.println("HTTP Request Failed with Response Code: " + connection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 to indicate failure
    }
}
