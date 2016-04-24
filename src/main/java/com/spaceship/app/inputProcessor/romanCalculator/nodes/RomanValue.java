package com.spaceship.app.inputProcessor.romanCalculator.nodes;

import java.util.Arrays;
import java.util.Optional;

public enum RomanValue {

    I(1f), V(5f), X(10f), L(50f), C(100f), D(500f), M(1000f);


    private float numericValue;

    RomanValue(float numericValue) {
        this.numericValue = numericValue;
    }


    public static boolean containsChar(Character character) {

        return Arrays.stream(RomanValue.values())
                .map(v -> v.name().charAt(0))
                .filter(c -> c == Character.toUpperCase(character))
                .count() > 0;

    }


    public static float findNumericValue(Character character) {
        Optional<Character> romanChar = Arrays.stream(RomanValue.values())
                .map(v -> v.name().charAt(0))
                .filter(c -> c == Character.toUpperCase(character))
                .findFirst();

        return romanChar.isPresent() ? RomanValue.valueOf(Character.toString(romanChar.get())).getNumericValue() : 0;
    }

    public float getNumericValue() {
        return this.numericValue;
    }
}
