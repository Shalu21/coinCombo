package com.example.coincombo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CoinComboConverterImplTest {

    @ParameterizedTest
    @MethodSource("dataForConvert")
    void testConvert(float amount, int silverDollar, int halfDollar, int quarter, int dime, int nickel, int penny) {
        final CoinComboConverter converter = new CoinComboConverterImpl();
        final CoinCombo combo = converter.convert(amount);
        assertEquals(silverDollar, combo.getSilverDollar());
        assertEquals(halfDollar, combo.getHalfDollar());
        assertEquals(quarter, combo.getQuarter());
        assertEquals(dime, combo.getDime());
        assertEquals(nickel, combo.getNickel());
        assertEquals(penny, combo.getPenny());
    }

    private static Stream<Arguments> dataForConvert() {
        return Stream.of(
            // amount, silverDollar, halfDollar, quarter, dime, nickel, penny
            Arguments.of(0.01f, 0, 0, 0, 0, 0, 1),
            Arguments.of(0.05f, 0, 0, 0, 0, 1, 0),
            Arguments.of(0.10f, 0, 0, 0, 1, 0, 0),
            Arguments.of(0.25f, 0, 0, 1, 0, 0, 0),
            Arguments.of(0.50f, 0, 1, 0, 0, 0, 0),
            Arguments.of(1.00f, 1, 0, 0, 0, 0, 0),
            Arguments.of(1f, 1, 0, 0, 0, 0, 0),
            Arguments.of(1.5f, 1, 1, 0, 0, 0, 0),
            Arguments.of(1.07f, 1, 0, 0, 0, 1, 2),
            Arguments.of(1.6f, 1, 1, 0, 1, 0, 0),
            Arguments.of(1.26f, 1, 0, 1, 0, 0, 1),
            Arguments.of(1.73f, 1, 1, 0, 2, 0, 3),
            Arguments.of(1.99f, 1, 1, 1, 2, 0, 4),
            Arguments.of(0f, 0, 0, 0, 0, 0, 0),
            Arguments.of(0.004f, 0, 0, 0, 0, 0, 0),
            Arguments.of(0.006f, 0, 0, 0, 0, 0, 0),
            Arguments.of(-1.31f, -1, 0, -1, 0, -1, -1), // negative counts for negative input
            Arguments.of(0.101f, 0, 0, 0, 1, 0, 0),
            Arguments.of(383.051f, 383, 0, 0, 0, 1, 0)
        );
    }
}