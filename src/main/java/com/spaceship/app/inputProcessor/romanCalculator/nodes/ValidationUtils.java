package com.spaceship.app.inputProcessor.romanCalculator.nodes;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class ValidationUtils {

    //validation fields

    /**
     * Characters that can never be repeated twice
     */
    private final static List<Character> notRepeatedChars = Arrays.asList('v', 'l', 'd');

    /**
     * Characters that can never appear more than 3 successive times, and no more times, taking into consideration the following:
     * They may appear four times if the third and the force are separated by smaller numeric value character
     */
    private final static List<Character> maxThreeRepeatedChars = Arrays.asList('i', 'x', 'c', 'm');


    private ValidationUtils() {
        //no construction
    }


    /**
     * Returns true if all characters in the roman string are roman characters
     *
     * @param romanString roman string to check
     * @return true if all chars are roman , false other wise
     */
    static boolean checkAllCharsRoman(String romanString) {

        //counts non roman chars found
        long charCount = romanString.chars()
                .mapToObj(i -> (char) i)
                .filter(c -> !RomanValue.containsChar(c))
                .count();


        return charCount > 0;
    }


    /**
     * Checks if one of the not repeated charachters appear more than once
     *
     * @param romanString roman string
     * @return true if any of the none repeating characters appears more than once
     */
    static boolean validateNotRepeatedChars(String romanString) {

        //filter only non repeating chars and group by their count
        Map<Character, Long> countMap = romanString.chars()
                .mapToObj(i -> (char) i)
                .filter(c -> notRepeatedChars.contains(c))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return countMap.values().stream().filter(v -> v > 1).count() > 0;
    }


    /**
     * Validates for characters that can never appear more than three successive times
     * and they may appear four times if the third and fourth times are separated by a smaller numeric value character
     *
     * @param romanString roman string to validate
     * @return true if valid, false otherwise
     */
    static boolean validateMaxThreeRepeatedChars(String romanString) {

        //check if there are more than three successive instance of any character
        for (Character c : maxThreeRepeatedChars) {
            //anything more than three successive times
            String fourSuccessivePattern = String.format("%s%s%s%s", c, c, c, c);

            if (romanString.contains(fourSuccessivePattern)) {
                return false;
            }
        }

        //check if any appears more than four (this is invalid)
        Map<Character, Long> countMap = romanString.chars()
                .mapToObj(i -> (char) i)
                .filter(c -> maxThreeRepeatedChars.contains(c))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        boolean characterAppearedMoreThan4 = countMap.values().stream().filter(v -> v > 4).count() > 0;
        if (characterAppearedMoreThan4) {
            return false;
        }

        //check if character appeared four times, it must follow the rule i.e. like XXXIX
        List<Character> appearedFour = countMap.entrySet().stream()
                .filter(e -> e.getValue() == 4)
                .map(e -> e.getKey())
                .collect(Collectors.toList());

        for (Character c : appearedFour) {
            String threeSuccessivePattern = String.format("%s%s%s", c, c, c);
            if (!romanString.contains(threeSuccessivePattern)) {
                return false;
            }

            int threeSuccessiveIndex = romanString.indexOf(threeSuccessivePattern);
            Character lowerChar = romanString.charAt(threeSuccessiveIndex + 3);
            Character fourthChar = romanString.charAt(threeSuccessiveIndex + 4);

            if (fourthChar != c) {
                return false; //the pattern is not correct
            }

            if (RomanValue.findNumericValue(lowerChar) >= RomanValue.findNumericValue(fourthChar)) {
                return false; // the separator between third and fourth is of greater value
            }

        }

        return true;
    }
}
