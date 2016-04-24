package com.spaceship;

import com.spaceship.app.inputProcessor.romanCalculator.RomanCalculator;
import com.spaceship.app.inputProcessor.romanCalculator.RomanCalculatorFactory;
import com.spaceship.app.inputProcessor.romanCalculator.RomanResultModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RomanCalculatorValidationTest {

    private RomanCalculator romanCalculator;

    @Before
    public void init() {
        romanCalculator = RomanCalculatorFactory.get();
    }


    /**
     * Tests validation of MCMXLIV should be valid
     */
    @Test
    public void testSuccessfullValidation() {

        RomanResultModel model = romanCalculator.calculateValue("MCMXLIV");

        Assert.assertTrue(model.getErrorMessage(), model.isValid());
    }

    /**
     * Tests validation of MCMALIB should be NOT valid
     */
    @Test
    public void testNonRomanCharacterValidation() {

        RomanResultModel model = romanCalculator.calculateValue("MCMALIB");

        Assert.assertFalse(model.getErrorMessage(), model.isValid());
    }

    /**
     * Tests validation repeating non repeatable characters
     * i.e. MCMDLDV -> D is repeated twice
     */
    @Test
    public void testRepeatedCharactersValidation() {

        RomanResultModel model = romanCalculator.calculateValue("MCMDLDV");

        Assert.assertFalse(model.getErrorMessage(), model.isValid());
    }


    /**
     * Tests validation repeating non repeatable characters
     * i.e. MCMDLDVLV -> D,L,V  repeated twice
     */
    @Test
    public void testMoreRepeatedCharactersValidation() {

        RomanResultModel model = romanCalculator.calculateValue("MCMDLDVLV");

        Assert.assertFalse(model.getErrorMessage(), model.isValid());
    }


    /**
     * Tests validation over repeating repeatable characters i.e. I X C M
     * i.e. MCMXXXLXC -> X repeated  4 times , 3rd and 4th separated by Higher value (correct)
     */
    @Test
    public void testOverReapeatingRepeatableChars4TimesFalse() {

        RomanResultModel model = romanCalculator.calculateValue("MCMXXXLXC");

        Assert.assertFalse(model.getErrorMessage(), model.isValid());
    }

    /**
     * Tests validation over repeating repeatable characters i.e. I X C M
     * i.e. MCMXDXXLXC -> X repeated  4 times , wrong pattern, i.e. no XXX
     */
    @Test
    public void testOverReapeatingRepeatableChars4TimesWrongPattern() {

        RomanResultModel model = romanCalculator.calculateValue("MCMXDXXLXC");

        Assert.assertFalse(model.getErrorMessage(), model.isValid());
    }


    /**
     * Tests validation over repeating repeatable characters i.e. I X C M
     * i.e. MCMXXXIXC -> X repeated  4 times , 3rd and 4th separated by lower value (correct)
     */
    @Test
    public void testOverReapeatingRepeatableChars4TimesCorrect() {

        RomanResultModel model = romanCalculator.calculateValue("MCMXXXIXC");

        Assert.assertTrue(model.getErrorMessage(), model.isValid());
    }


    /**
     * Tests validation over repeating repeatable characters i.e. I X C M
     * i.e. MCMXXXXC -> X repeated  4 times successively
     */
    @Test
    public void testOverReapeatingRepeatableChars4TimesSuccessively() {

        RomanResultModel model = romanCalculator.calculateValue("MCMXXXXC");

        Assert.assertFalse(model.getErrorMessage(), model.isValid());
    }

    /**
     * Tests validation over repeating repeatable characters i.e. I X C M
     * i.e. MCMXXXIXXC -> X repeated more more than 4 times (3rd and 4th are correct but the fifth is not valid)
     */
    @Test
    public void testOverReapeatingRepeatableChars5Times() {

        RomanResultModel model = romanCalculator.calculateValue("MCMXXXIXXC");

        Assert.assertFalse(model.getErrorMessage(), model.isValid());
    }
}
