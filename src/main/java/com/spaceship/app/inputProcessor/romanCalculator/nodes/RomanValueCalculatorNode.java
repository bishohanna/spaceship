package com.spaceship.app.inputProcessor.romanCalculator.nodes;

import com.spaceship.app.inputProcessor.romanCalculator.RomanResultModel;
import com.spaceship.tools.baseChain.ChainNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Makes the real calculation logic
 */
public class RomanValueCalculatorNode implements ChainNode<RomanResultModel, String> {

    private static final Map<RomanValue, List<RomanValue>> subtractionMap = new HashMap<>();

    static {
        subtractionMap.put(RomanValue.I, Arrays.asList(RomanValue.V, RomanValue.X));
        subtractionMap.put(RomanValue.X, Arrays.asList(RomanValue.L, RomanValue.C));
        subtractionMap.put(RomanValue.C, Arrays.asList(RomanValue.D, RomanValue.M));
    }

    @Override
    public void process(RomanResultModel model, String romanString) {
        if (model.isValid()) {

            float result = 0;

            char[] characters = romanString.toCharArray();

            for (int i = 0; i < characters.length; i++) {

                if (i == characters.length - 1) {

                    result += RomanValue.findNumericValue(characters[i]);
                } else {

                    result += calculateNumericValue(characters[i], characters[i + 1]);
                }

            }

            model.setNumericResult(result);

        }
    }

    private float calculateNumericValue(char current, char next) {

        float currentValue = RomanValue.findNumericValue(current);
        float nextValue = RomanValue.findNumericValue(next);
        RomanValue currentRoman = RomanValue.valueOf(Character.toString(current).toUpperCase());
        RomanValue nextRoman = RomanValue.valueOf(Character.toString(next).toUpperCase());

        if ((currentValue < nextValue) && subtractionMap.containsKey(currentRoman)) {

            if (subtractionMap.get(currentRoman).contains(nextRoman)) {
                return -currentValue;
            } else {
                return currentValue;
            }

        } else {
            return currentValue;
        }
    }
}
