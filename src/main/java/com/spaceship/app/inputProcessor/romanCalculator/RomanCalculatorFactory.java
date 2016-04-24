package com.spaceship.app.inputProcessor.romanCalculator;

public final class RomanCalculatorFactory {

    private static final RomanCalculator ROMAN_CALCULATOR = new RomanCalculatorContext();

    private RomanCalculatorFactory() {
        //no construction
    }


    public static RomanCalculator get() {
        return ROMAN_CALCULATOR;
    }
}
