package com.spaceship;

import com.spaceship.app.inputParser.InputParser;
import com.spaceship.app.inputParser.InputParserFactory;
import com.spaceship.app.inputParser.InputParserModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InputParserTest {

    private InputParser inputParser;

    @Before
    public void init() {
        inputParser = InputParserFactory.get();
    }


    @Test
    public void testParseInput() {

        InputParserModel model = inputParser.parseFile(getClass().getResource("/default_input.txt").getFile());

        Assert.assertNotNull("Model shouldn't be null", model);
        Assert.assertEquals("Definitions should be 4", 4, model.getDefinitions().size());
        Assert.assertEquals("Missing values should be 3", 3, model.getMissingValues().size());
        Assert.assertEquals("Questions should be  5", 5, model.getQuestions().size());

    }
}
