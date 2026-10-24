package org.example;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocalizationApp  {
    private LocalizationApp() {
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(
                    "Select language / Valitse kieli / Välj språk / 言語を選択:");
            System.out.println("1. English");
            System.out.println("2. Suomi");
            System.out.println("3. Svenska");
            System.out.println("4. 日本語");

            String language = scanner.nextLine().trim();
            Locale locale;

            switch (language) {
                case "2":
                    locale = new Locale("fi", "FI");
                    break;
                case "3":
                    locale = new Locale("sv", "SE");
                    break;
                case "4":
                    locale = new Locale("ja", "JP");
                    break;
                default:
                    locale = Locale.US;
            }

            ResourceBundle messages =
                    ResourceBundle.getBundle("MessagesBundle", locale);

            TemperatureConverter converter = new TemperatureConverter();

            System.out.println(messages.getString("welcome"));
            System.out.println(messages.getString("menu"));
            String operation = scanner.nextLine().trim();

            if (!operation.equals("1") && !operation.equals("2")) {
                System.out.println(messages.getString("invalidOption"));
                return;
            }

            String prompt = operation.equals("1")
                    ? "enterFahrenheit"
                    : "enterCelsius";

            System.out.println(messages.getString(prompt));

            try {
                // Accept either a decimal point or a decimal comma.
                String input = scanner.nextLine().trim().replace(',', '.');
                double temperature = Double.parseDouble(input);

                if (!Double.isFinite(temperature)) {
                    System.out.println(messages.getString("invalidNumber"));
                    return;
                }

                double celsius;

                if (operation.equals("1")) {
                    celsius = converter.fahrenheitToCelsius(temperature);
                    System.out.printf(
                            locale,
                            messages.getString("resultCelsius") + "%n",
                            celsius);
                } else {
                    celsius = temperature;
                    double fahrenheit =
                            converter.celsiusToFahrenheit(celsius);

                    System.out.printf(
                            locale,
                            messages.getString("resultFahrenheit") + "%n",
                            fahrenheit);
                }

                if (converter.isExtremeTemperature(celsius)) {
                    System.out.println(messages.getString("extreme"));
                }
            } catch (NumberFormatException e) {
                System.out.println(messages.getString("invalidNumber"));
            }
        }
    }
}