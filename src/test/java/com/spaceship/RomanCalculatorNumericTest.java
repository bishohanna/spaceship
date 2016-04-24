package com.spaceship;

import com.spaceship.app.inputProcessor.romanCalculator.RomanCalculator;
import com.spaceship.app.inputProcessor.romanCalculator.RomanCalculatorFactory;
import com.spaceship.app.inputProcessor.romanCalculator.RomanResultModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RomanCalculatorNumericTest {

    private RomanCalculator romanCalculator;

    @Before
    public void init() {
        romanCalculator = RomanCalculatorFactory.get();
    }


    /**
     * Tests calculation
     */
    @Test
    public void testSuccessfullCalculation() {

        RomanResultModel model1 = romanCalculator.calculateValue("MCMXLIV");
        Assert.assertEquals("Result should be 1944", 1944, model1.getNumericResult(), 0);

        RomanResultModel model2 = romanCalculator.calculateValue("DCMVXXIL");
        Assert.assertEquals("Result should be 1476", 1476, model2.getNumericResult(), 0);

        RomanResultModel model3 = romanCalculator.calculateValue("LCDXXXIXM");
        Assert.assertEquals("Result should be 1489", 1489, model3.getNumericResult(), 0);
    }


}
