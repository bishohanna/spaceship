package com.spaceship;

import com.spaceship.app.inputParser.InputParser;
import com.spaceship.app.inputParser.InputParserFactory;
import com.spaceship.app.inputParser.InputParserModel;
import com.spaceship.app.inputProcessor.InputProcessor;
import com.spaceship.app.inputProcessor.InputProcessorFactory;
import com.spaceship.app.inputProcessor.ProcessorResultModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InputProcessorTest {

    private InputParser inputParser;
    private InputProcessor inputProcessor;

    @Before
    public void init() {
        inputParser = InputParserFactory.get();
        inputProcessor = InputProcessorFactory.get();
    }

    @Test
    public void testInputProcessor() {

        InputParserModel inputParserModel = inputParser.parseFile(getClass().getResource("/default_input.txt").getFile());

        ProcessorResultModel processorResultModel = inputProcessor.process(inputParserModel);


        Assert.assertNotNull("Definitions shouldn't be null", processorResultModel.getDefinitionsMap());
        Assert.assertNotNull("Missing values shouldn't be null", processorResultModel.getMissingValuesMap());
        Assert.assertNotNull("Questions should be answered", processorResultModel.getQuestionsAndAnswersMap());
    }

}
