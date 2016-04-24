package com.spaceship.app.inputProcessor.romanCalculator;

/**
 * Calculates float value from roman string
 */
public interface RomanCalculator {

    /**
     * Evaluates float value from roman string
     *
     * @param romanString roman string
     * @return roman result, indicating the value if valid, and the reason if not valid
     */
    RomanResultModel calculateValue(String romanString);

}
