import org.example.TemperatureConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterTest {

    private final TemperatureConverter converter = new TemperatureConverter();
    private static final double DELTA = 0.000001;

    @Test
    void fahrenheitToCelsiusWithMultipleInputs() {
        assertAll(
                () -> assertEquals(0, converter.fahrenheitToCelsius(32), DELTA),
                () -> assertEquals(100, converter.fahrenheitToCelsius(212), DELTA),
                () -> assertEquals(-40, converter.fahrenheitToCelsius(-40), DELTA),
                () -> assertEquals(37, converter.fahrenheitToCelsius(98.6), DELTA)
        );
    }

    @Test
    void celsiusToFahrenheitWithMultipleInputs() {
        assertAll(
                () -> assertEquals(32, converter.celsiusToFahrenheit(0), DELTA),
                () -> assertEquals(212, converter.celsiusToFahrenheit(100), DELTA),
                () -> assertEquals(-40, converter.celsiusToFahrenheit(-40), DELTA),
                () -> assertEquals(98.6, converter.celsiusToFahrenheit(37), DELTA)
        );
    }

    @Test
    void isExtremeTemperatureAtBoundaries() {
        assertAll(
                () -> assertFalse(converter.isExtremeTemperature(-40)),
                () -> assertFalse(converter.isExtremeTemperature(50))
        );
    }

    @Test
    void isExtremeTemperatureOutsideBoundaries() {
        assertAll(
                () -> assertTrue(converter.isExtremeTemperature(-41)),
                () -> assertTrue(converter.isExtremeTemperature(51)),
                () -> assertTrue(converter.isExtremeTemperature(-40.01)),
                () -> assertTrue(converter.isExtremeTemperature(50.01))
        );
    }

    @Test
    void isExtremeTemperatureWithinNormalRange() {
        assertAll(
                () -> assertFalse(converter.isExtremeTemperature(-39.99)),
                () -> assertFalse(converter.isExtremeTemperature(49.99)),
                () -> assertFalse(converter.isExtremeTemperature(0))
        );
    }
}