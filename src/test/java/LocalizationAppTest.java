

import org.example.LocalizationApp;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalizationAppTest {

    @ParameterizedTest
    @CsvSource({
            "1, Welcome to the Temperature Converter!",
            "2, Tervetuloa lämpötilamuuntimeen!",
            "3, Välkommen till temperaturomvandlaren!",
            "4, 温度変換アプリへようこそ！",
            "9, Welcome to the Temperature Converter!"
    })
    void selectsLanguage(String language, String expectedWelcome) {
        String output = runApp(language + "\n1\n32\n");

        assertTrue(output.contains(expectedWelcome), output);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 32,     Temperature in Celsius: 0.00",
            "2, 100,    Temperature in Fahrenheit: 212.00",
            "1, '98,6', Temperature in Celsius: 37.00",
            "2, -41,    Warning: Extreme temperature!",
            "2, 51,     Warning: Extreme temperature!"
    })
    void convertsTemperature(
            String operation,
            String temperature,
            String expectedOutput) {

        String output = runApp(
                "1\n" + operation + "\n" + temperature + "\n"
        );

        assertTrue(output.contains(expectedOutput), output);
    }

    @ParameterizedTest
    @CsvSource({
            "3, unused,   Invalid option.",
            "1, abc,      Invalid temperature.",
            "1, NaN,      Invalid temperature.",
            "2, Infinity, Invalid temperature."
    })
    void rejectsInvalidInput(
            String operation,
            String temperature,
            String expectedOutput) {

        String output = runApp(
                "1\n" + operation + "\n" + temperature + "\n"
        );

        assertTrue(output.contains(expectedOutput), output);
    }

    private String runApp(String input) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream capturedOutput = new ByteArrayOutputStream();

        try (PrintStream testOut = new PrintStream(
                capturedOutput, true, StandardCharsets.UTF_8)) {

            System.setIn(new ByteArrayInputStream(
                    input.getBytes(StandardCharsets.UTF_8)));
            System.setOut(testOut);

            LocalizationApp.main(new String[0]);

            testOut.flush();
            return capturedOutput.toString(StandardCharsets.UTF_8);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}