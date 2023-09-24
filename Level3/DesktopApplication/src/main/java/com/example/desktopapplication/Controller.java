package com.example.desktopapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Controller {
    private static final String API_KEY = "89335cef1169e8d35fde69ef"; // Replace with your API key

    @FXML
    private ComboBox<String> fromCurrencyComboBox;

    @FXML
    private ComboBox<String> toCurrencyComboBox;

    @FXML
    private TextField amountTextField;

    @FXML
    private Label resultLabel;

    @FXML
    private Button convertButton;

    public void initialize() {
        // Initialize ComboBoxes and other UI components
        fromCurrencyComboBox.getItems().addAll("AED","AFN","XCD","ALL","AMD","ANG","AOA","AQD","ARS","AUD","AZN","BAM","BBD","BDT","XOF","BGN","BHD","BIF","BMD","BND","BOB","BRL","BSD","NOK","BWP","BYR","BZD","CAD","CDF","XAF","CHF","CLP","CNY","COP","CRC","CUP","CVE","CYP","CZK","DJF","DKK","DOP","DZD","ECS","EEK","EGP","ETB","EUR","FJD","FKP","GBP","GEL","GGP","GHS","GIP","GMD","GNF","GTQ","GYD","HKD","HNL","HRK","HTG","HUF","IDR","ILS","INR","IQD","IRR","ISK","JMD","JOD","JPY","KES","KGS","KHR","KMF","KPW","KRW","KWD","KYD","KZT","LAK","LBP","LKR","LRD","LSL","LTL","LVL","LYD","MAD","MDL","MGA","MKD","MMK","MNT","MOP","MRO","MTL","MUR","MVR","MWK","MXN","MYR","MZN","NAD","XPF","NGN","NIO","NPR","NZD","OMR","PAB","PEN","PGK","PHP","PKR","PLN","PYG","QAR","RON","RSD","RUB","RWF","SAR","SBD","SCR","SDG","SEK","SGD","SKK","SLL","SOS","SRD","STD","SVC","SYP","SZL","THB","TJS","TMT","TND","TOP","TRY","TTD","TWD","TZS","UAH","UGX","USD","UYU","UZS","VEF","VND","VUV","YER","ZAR","ZMK","ZWD");
        toCurrencyComboBox.getItems().addAll("AED","AFN","XCD","ALL","AMD","ANG","AOA","AQD","ARS","AUD","AZN","BAM","BBD","BDT","XOF","BGN","BHD","BIF","BMD","BND","BOB","BRL","BSD","NOK","BWP","BYR","BZD","CAD","CDF","XAF","CHF","CLP","CNY","COP","CRC","CUP","CVE","CYP","CZK","DJF","DKK","DOP","DZD","ECS","EEK","EGP","ETB","EUR","FJD","FKP","GBP","GEL","GGP","GHS","GIP","GMD","GNF","GTQ","GYD","HKD","HNL","HRK","HTG","HUF","IDR","ILS","INR","IQD","IRR","ISK","JMD","JOD","JPY","KES","KGS","KHR","KMF","KPW","KRW","KWD","KYD","KZT","LAK","LBP","LKR","LRD","LSL","LTL","LVL","LYD","MAD","MDL","MGA","MKD","MMK","MNT","MOP","MRO","MTL","MUR","MVR","MWK","MXN","MYR","MZN","NAD","XPF","NGN","NIO","NPR","NZD","OMR","PAB","PEN","PGK","PHP","PKR","PLN","PYG","QAR","RON","RSD","RUB","RWF","SAR","SBD","SCR","SDG","SEK","SGD","SKK","SLL","SOS","SRD","STD","SVC","SYP","SZL","THB","TJS","TMT","TND","TOP","TRY","TTD","TWD","TZS","UAH","UGX","USD","UYU","UZS","VEF","VND","VUV","YER","ZAR","ZMK","ZWD");
        fromCurrencyComboBox.setValue("USD");
        toCurrencyComboBox.setValue("INR");
    }

    @FXML
    private void handleConvertButton(ActionEvent event) {
        try {
            String fromCurrency = fromCurrencyComboBox.getValue();
            String toCurrency = toCurrencyComboBox.getValue();
            double amount = Double.parseDouble(amountTextField.getText());
            double convertedAmount = convertCurrency(fromCurrency, toCurrency, amount);
            resultLabel.setText(String.format("%.2f %s", convertedAmount, toCurrency));
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input");
        }
    }

    private double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        try {
            String urlStr = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + fromCurrency + "/" + toCurrency;
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
                double exchangeRate = jsonResponse.getDouble("conversion_rate");
                return amount * exchangeRate;
            } else {
                System.err.println("HTTP Request Failed with Response Code: " + connection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 to indicate failure
    }
}
